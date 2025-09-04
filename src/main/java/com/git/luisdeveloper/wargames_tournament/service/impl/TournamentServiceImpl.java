package com.git.luisdeveloper.wargames_tournament.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.git.luisdeveloper.wargames_tournament.entity.Tournament;
import com.git.luisdeveloper.wargames_tournament.exception.TournamentNotFoundException;
import com.git.luisdeveloper.wargames_tournament.repository.TournamentRepository;
import com.git.luisdeveloper.wargames_tournament.service.TournamentService;

@Service
public class TournamentServiceImpl implements TournamentService{

	@Autowired
	private TournamentRepository repository;
	
	@Override
	public List<Tournament> getTournaments() {
		return repository.findAll();
	}

	@Override
	public Tournament getTournament(Long id) throws TournamentNotFoundException {
		Optional<Tournament> Tournament = repository.findById(id); 
		if(Tournament.isEmpty()) {
			throw new TournamentNotFoundException();
		}
		return Tournament.get();
	}

	@Override
	public void createTournament(Tournament Tournament) {
		repository.save(Tournament);
	}

	@Override
	public void updateTournament(Tournament Tournament) throws TournamentNotFoundException {
		if(repository.findById(Tournament.getId()).isEmpty()) {
			throw new TournamentNotFoundException();
		}
		repository.save(Tournament);
	}

	@Override
	public void deleteTournament(Tournament Tournament) throws TournamentNotFoundException {
		if(repository.findById(Tournament.getId()).isEmpty()) {
			throw new TournamentNotFoundException();
		}
		repository.delete(Tournament);
	}

}
