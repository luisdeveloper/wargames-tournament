package com.git.luisdeveloper.wargames_tournament.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.git.luisdeveloper.wargames_tournament.dto.RoundDTO;
import com.git.luisdeveloper.wargames_tournament.dto.UpdateRoundDTO;
import com.git.luisdeveloper.wargames_tournament.entity.Match;
import com.git.luisdeveloper.wargames_tournament.entity.Player;
import com.git.luisdeveloper.wargames_tournament.entity.Round;
import com.git.luisdeveloper.wargames_tournament.enums.MatchResult;
import com.git.luisdeveloper.wargames_tournament.exception.NoPendingRoundsException;
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

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	public RoundServiceImpl() {

	}

	@Override
	public void updateDates(UpdateRoundDTO dto) {
		repository.updateDates(dto.roundId(), dto.roundDate(), dto.beginTime(), dto.endTime());
	}

	@Override
	@Transactional
	public RoundDTO generateMatches(Long tournamentId, List<Player> players) throws NoPendingRoundsException {
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
		return RoundMapper.toDto(round);
	}

	private boolean solveByeMatch(Player player, List<Match> matches) {
		Match match = new Match(player, null, MatchResult.BYE);
		matchInternalService.solveMatch(match.getId(), player.getId(), null, match.getResult());
		return matches.add(match);
	}

	@Override
	public RoundDTO findFirstPendingRoundDTO(Long tournamentId) throws NoPendingRoundsException {
		Round round = findFirstPendingRound(tournamentId);
		return RoundMapper.toDto(round);
	}
	
	private Round findFirstPendingRound(Long tournamentId) throws NoPendingRoundsException {
		return repository.findFirstPendingRound(tournamentId).orElseThrow(NoPendingRoundsException::new);
	}

}
