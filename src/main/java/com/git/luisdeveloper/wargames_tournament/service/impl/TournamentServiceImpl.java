package com.git.luisdeveloper.wargames_tournament.service.impl;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.git.luisdeveloper.wargames_tournament.dto.PlayerRegistrationDTO;
import com.git.luisdeveloper.wargames_tournament.dto.RoundDTO;
import com.git.luisdeveloper.wargames_tournament.dto.TournamentRegistrationDTO;
import com.git.luisdeveloper.wargames_tournament.dto.TournamentSummaryDTO;
import com.git.luisdeveloper.wargames_tournament.entity.Tournament;
import com.git.luisdeveloper.wargames_tournament.exception.NoPendingRoundsException;
import com.git.luisdeveloper.wargames_tournament.exception.TournamentNotFoundException;
import com.git.luisdeveloper.wargames_tournament.logging.ServiceLogFormatter;
import com.git.luisdeveloper.wargames_tournament.mappers.PlayerMapper;
import com.git.luisdeveloper.wargames_tournament.mappers.TournamentMapper;
import com.git.luisdeveloper.wargames_tournament.repository.TournamentRepository;
import com.git.luisdeveloper.wargames_tournament.service.RoundService;
import com.git.luisdeveloper.wargames_tournament.service.TournamentService;

import jakarta.transaction.Transactional;

@Service
public class TournamentServiceImpl implements TournamentService {

	@Autowired
	private RoundService roundService;

	@Autowired
	private TournamentRepository repository;

	@Autowired
	private ServiceLogFormatter formatter;

	private static Logger logger = Logger.getLogger(TournamentServiceImpl.class.getName());

	@Override
	public TournamentSummaryDTO getTournament(Long id) throws TournamentNotFoundException {
		logger.fine(formatter.request("Getting tournament summary with id: " + id));
		Tournament tournament = repository.findById(id).orElseThrow(TournamentNotFoundException::new);
		var tournamentSummaryDTO = TournamentMapper.toTournamentSummaryDTO(tournament);
		logger.fine(formatter.success("Getting tournament summary with id: " + id));
		return tournamentSummaryDTO;
	}

	@Override
	@Transactional
	public TournamentSummaryDTO createTournament(TournamentRegistrationDTO tournamentRegistrationDTO) {
		logger.fine(formatter.request("Creating tournament"));
		Tournament tournament = TournamentMapper.toEntity(tournamentRegistrationDTO);
		tournament = repository.save(tournament);
		var tournamentSummaryDTO = TournamentMapper.toTournamentSummaryDTO(tournament);
		logger.fine(formatter.success("Creating tournament"));
		return tournamentSummaryDTO;
	}

	@Override
	public void deleteTournament(Long id) throws TournamentNotFoundException {
		logger.fine(formatter.request("Deleting tournament with id: " + id));
		if (repository.existsById(id)) {
			repository.deleteById(id);
			logger.fine(formatter.success("Deleting tournament with id: " + id));
		} else {
			logger.fine(formatter.error("Deleting tournament with id: " + id));
			throw new TournamentNotFoundException();
		}
			
	}

	@Override
	public RoundDTO getCurrentRound(Long tournamentId) throws NoPendingRoundsException {
		logger.fine(formatter.request("Getting current round of tournament with id: " + tournamentId));
		var firstPendingRoundDTO = roundService.findFirstPendingRoundDTO(tournamentId);
		logger.fine(formatter.success("Getting current round of tournament with id: " + tournamentId));
		return firstPendingRoundDTO;
	}

	@Override
	@Transactional
	public RoundDTO generateMatches(Long tournamentId) throws TournamentNotFoundException, NoPendingRoundsException {
		logger.fine(formatter.request("Generating matches for tournament with id: " + tournamentId));
		Tournament tournament = repository.findById(tournamentId).orElseThrow(TournamentNotFoundException::new);
		loadPlayers(tournament);
		final RoundDTO newMatches = roundService.generateMatches(tournamentId, tournament.getCompetitors());
		logger.fine(formatter.success("Generating matches for tournament with id: " + tournamentId));
		return newMatches;
	}

	private void loadPlayers(Tournament tournament) {
		tournament.getCompetitors().size();
	}

	@Override
	@Transactional
	public void addPlayer(PlayerRegistrationDTO player) throws TournamentNotFoundException {
		logger.fine(formatter.request("adding player for tournament with id: " + player.tournamentId()));
		Tournament tournament = repository.findById(player.tournamentId())
				.orElseThrow(TournamentNotFoundException::new);
		tournament.getCompetitors().add(PlayerMapper.toEntity(player));
		logger.fine(formatter.success("adding player for tournament with id: " + player.tournamentId()));
	}

}
