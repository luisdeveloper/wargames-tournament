package com.git.luisdeveloper.wargames_tournament.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.git.luisdeveloper.wargames_tournament.dto.PlayerRegistrationDTO;
import com.git.luisdeveloper.wargames_tournament.dto.RoundDTO;
import com.git.luisdeveloper.wargames_tournament.dto.TournamentRegistrationDTO;
import com.git.luisdeveloper.wargames_tournament.dto.TournamentSummaryDTO;
import com.git.luisdeveloper.wargames_tournament.entity.Tournament;
import com.git.luisdeveloper.wargames_tournament.exception.TournamentNotFoundException;
import com.git.luisdeveloper.wargames_tournament.exceptions.NoPendingRoundsException;
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

	@Override
	public TournamentSummaryDTO getTournament(Long id) throws TournamentNotFoundException {
		Tournament tournament = repository.findById(id).orElseThrow(TournamentNotFoundException::new);
		return TournamentMapper.toTournamentSummaryDTO(tournament);
	}

	@Override
	public TournamentSummaryDTO createTournament(TournamentRegistrationDTO tournamentRegistrationDTO) {
		Tournament tournament = TournamentMapper.toEntity(tournamentRegistrationDTO);
		tournament = repository.save(tournament);
		return TournamentMapper.toTournamentSummaryDTO(tournament);
	}

	@Override
	public void deleteTournament(Long id) throws TournamentNotFoundException {
		repository.deleteById(id);
	}

	@Override
	public RoundDTO getCurrentRound(Long tournamentId) throws NoPendingRoundsException {
		return roundService.findFirstPendingRoundDTO(tournamentId);
	}

	@Override
	public RoundDTO generateMatches(Long tournamentId) throws TournamentNotFoundException, NoPendingRoundsException {
		Tournament tournament = repository.findById(tournamentId).orElseThrow(TournamentNotFoundException::new);
		return roundService.generateMatches(tournamentId, tournament.getCompetitors());
	}

	@Override
	@Transactional
	public void addPlayer(PlayerRegistrationDTO player) throws TournamentNotFoundException {
		Tournament tournament = repository.findById(player.tournamentId())
				.orElseThrow(TournamentNotFoundException::new);
		tournament.getCompetitors().add(PlayerMapper.toEntity(player));
	}

}
