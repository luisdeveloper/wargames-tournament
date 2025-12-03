package com.git.luisdeveloper.wargames_tournament.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.git.luisdeveloper.wargames_tournament.dto.MatchDTO;
import com.git.luisdeveloper.wargames_tournament.dto.RoundDTO;
import com.git.luisdeveloper.wargames_tournament.dto.UpdateRoundDTO;
import com.git.luisdeveloper.wargames_tournament.entity.Match;
import com.git.luisdeveloper.wargames_tournament.entity.Player;
import com.git.luisdeveloper.wargames_tournament.entity.Round;
import com.git.luisdeveloper.wargames_tournament.enums.MatchResult;
import com.git.luisdeveloper.wargames_tournament.exception.NoPendingRoundsException;
import com.git.luisdeveloper.wargames_tournament.exception.RoundNotFoundException;
import com.git.luisdeveloper.wargames_tournament.logging.ServiceLogFormatter;
import com.git.luisdeveloper.wargames_tournament.mappers.RoundMapper;
import com.git.luisdeveloper.wargames_tournament.repository.RoundRepository;
import com.git.luisdeveloper.wargames_tournament.service.RoundService;

import jakarta.transaction.Transactional;

@Service
public class RoundServiceImpl implements RoundService {

	@Autowired
	private RoundRepository repository;

	@Autowired
	private MatchInternalService matchInternalService;

	@Autowired
	private ServiceLogFormatter formatter;

	private static Logger logger = Logger.getLogger(RoundServiceImpl.class.getName());

	@Override
	public void delete(Long id) throws RoundNotFoundException {
		logger.fine(formatter.request("Deleting round"));
		if (repository.existsById(id)) {
			repository.deleteById(id);
			logger.fine(formatter.success("Deleting round"));
		} else
			throw new RoundNotFoundException();
	}

	@Override
	@Transactional
	public void updateDates(UpdateRoundDTO dto) throws RoundNotFoundException {
		logger.fine(formatter.request("Updating round dates with round id: " + dto.roundId()));
		int roundsUpdated = repository.updateDates(dto.roundId(), dto.roundDate(), dto.beginTime(), dto.endTime());
		if (roundsUpdated == 0)
			throw new RoundNotFoundException();
		logger.fine(formatter.success("Updating round dates with round id: " + dto.roundId()));
	}

	@Override
	@Transactional
	public RoundDTO generateMatches(Long tournamentId, List<Player> players) throws NoPendingRoundsException {
		logger.fine(formatter.request("Generating matches for tournament with id: " + tournamentId));
		Round round = findFirstPendingRound(tournamentId);
		players = players.stream().sorted(Comparator.comparing(Player::getPoints).reversed()).toList();
		List<Match> newMatches = IntStream.range(0, players.size()).boxed()
				.collect(Collectors.teeing(
						Collectors.filtering(x -> x % 2 == 0, Collectors.mapping(players::get, Collectors.toList())),
						Collectors.filtering(x -> x % 2 != 0, Collectors.mapping(players::get, Collectors.toList())),
						(a, b) -> {
							List<Match> l = new ArrayList<Match>();
							for (int i = 0; i < b.size(); i++) {
								l.add(new Match(a.get(i), b.get(i)));
							}
							if (a.size() > b.size())
								solveByeMatch(a.get(a.size() - 1), l);
							return l;
						}));

		round.getMatches().addAll(newMatches);
		round = repository.save(round);
		var dto = RoundMapper.toDto(round);
		logger.fine(formatter.success("Generating matches for tournament with id: " + tournamentId));
		return dto;
	}

	private boolean solveByeMatch(Player player, List<Match> matches) {
		logger.finer(formatter.request("Solving bye matches"));
		Match match = new Match(player, null, MatchResult.BYE);
		matchInternalService.solveMatch(match.getId(), player.getId(), null, match.getResult());
		logger.finer(formatter.success("Solving bye matches"));
		return matches.add(match);
	}

	@Override
	@Transactional
	public RoundDTO findFirstPendingRoundDTO(Long tournamentId) throws NoPendingRoundsException {
		logger.fine(formatter.request("Finding first pending round for tournament with id: " + tournamentId));
		Round round = findFirstPendingRound(tournamentId);
		loadMatches(round);
		var dto = RoundMapper.toDto(round);
		logger.fine(formatter.success("Finding first pending round for tournament with id: " + tournamentId));
		return dto;
	}

	private void loadMatches(Round round) {
		round.getMatches().size();
	}

	private Round findFirstPendingRound(Long tournamentId) throws NoPendingRoundsException {
		logger.finer(formatter.request("Finding first pending round for tournament with id: " + tournamentId));
		final Round round = repository.findFirstPendingRound(tournamentId).orElseThrow(NoPendingRoundsException::new);
		logger.finer(formatter.success("Finding first pending round for tournament with id: " + tournamentId));
		return round;
	}

	@Override
	public List<MatchDTO> getMatches(Long roundId) throws RoundNotFoundException {
		logger.fine(formatter.request("Getting matches for round with id: " + roundId));
		if (repository.existsById(roundId)) {
			var matches = matchInternalService.getMatches(roundId);
			logger.fine(formatter.success("Getting matches for round with id: " + roundId));
			return matches;
		}
		throw new RoundNotFoundException();
	}

}
