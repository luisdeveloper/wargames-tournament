package com.git.luisdeveloper.wargames_tournament.service;

import com.git.luisdeveloper.wargames_tournament.dto.PlayerRegistrationDTO;
import com.git.luisdeveloper.wargames_tournament.dto.RoundDTO;
import com.git.luisdeveloper.wargames_tournament.dto.TournamentRegistrationDTO;
import com.git.luisdeveloper.wargames_tournament.dto.TournamentSummaryDTO;
import com.git.luisdeveloper.wargames_tournament.exception.TournamentNotFoundException;
import com.git.luisdeveloper.wargames_tournament.exceptions.NoPendingRoundsException;

public interface TournamentService {

	public TournamentSummaryDTO getTournament(Long id) throws TournamentNotFoundException;

	public TournamentSummaryDTO createTournament(TournamentRegistrationDTO tournament);

	public void deleteTournament(Long id) throws TournamentNotFoundException;

	public RoundDTO getCurrentRound(Long tournamentId) throws NoPendingRoundsException;

	public RoundDTO generateMatches(Long tournamentId) throws TournamentNotFoundException, NoPendingRoundsException;

	public void addPlayer(PlayerRegistrationDTO player) throws TournamentNotFoundException;
}
