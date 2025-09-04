package com.git.luisdeveloper.wargames_tournament.service;

import java.util.List;

import com.git.luisdeveloper.wargames_tournament.entity.Tournament;
import com.git.luisdeveloper.wargames_tournament.exception.TournamentNotFoundException;

public interface TournamentService {
public List<Tournament> getTournaments();
	
	public Tournament getTournament(Long id) throws TournamentNotFoundException;
	
	public void createTournament(Tournament Tournament);
	
	public void updateTournament(Tournament Tournament) throws TournamentNotFoundException;
	
	public void deleteTournament(Tournament Tournament) throws TournamentNotFoundException;
}
