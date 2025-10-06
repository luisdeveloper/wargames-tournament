package com.git.luisdeveloper.wargames_tournament.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.git.luisdeveloper.wargames_tournament.dto.RoundDTO;
import com.git.luisdeveloper.wargames_tournament.dto.TournamentRegistrationDTO;
import com.git.luisdeveloper.wargames_tournament.dto.TournamentSummaryDTO;
import com.git.luisdeveloper.wargames_tournament.exception.NoPendingRoundsException;
import com.git.luisdeveloper.wargames_tournament.exception.RoundAlreadyStartedException;
import com.git.luisdeveloper.wargames_tournament.exception.TournamentNotFoundException;
import com.git.luisdeveloper.wargames_tournament.service.TournamentService;

@RestController
public class TournamentController {
	private static final String TOURNAMENT_SUCCESFULLY_DELETED = "Tournament succesfully deleted";

	@Autowired
	private TournamentService service;

	@GetMapping("/tournaments/{id}/summary")
	public ResponseEntity<TournamentSummaryDTO> getTournamentSummary(@PathVariable long id)
			throws TournamentNotFoundException {
		return ResponseEntity.ofNullable(service.getTournament(id));
	}

	@GetMapping("/tournaments/{id}/current-round")
	public ResponseEntity<RoundDTO> getCurrentRound(@PathVariable Long id) throws TournamentNotFoundException, NoPendingRoundsException {
		return ResponseEntity.ofNullable(service.getCurrentRound(id));
	}

	@PostMapping("/tournaments")
	public ResponseEntity<TournamentSummaryDTO> insertTournament(@RequestBody TournamentRegistrationDTO tournament) {
		TournamentSummaryDTO dto = service.createTournament(tournament);
		return ResponseEntity.ofNullable(dto);
	}
	
	@PostMapping("/tournaments/{tournamentId}/current-round/matches")
	public ResponseEntity<RoundDTO> generateMatches(@PathVariable Long tournamentId) throws TournamentNotFoundException, RoundAlreadyStartedException, NoPendingRoundsException {
		RoundDTO dto = service.generateMatches(tournamentId);
		return ResponseEntity.ofNullable(dto);
	}

	@DeleteMapping("/tournaments/{id}")
	public ResponseEntity<String> deleteTournament(@PathVariable Long id) {
		try {
			service.deleteTournament(id);
			return ResponseEntity.status(HttpStatus.OK).body(TOURNAMENT_SUCCESFULLY_DELETED);
		} catch (TournamentNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
