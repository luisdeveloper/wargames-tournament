package com.git.luisdeveloper.wargames_tournament.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.git.luisdeveloper.wargames_tournament.entity.Tournament;
import com.git.luisdeveloper.wargames_tournament.exception.TournamentNotFoundException;
import com.git.luisdeveloper.wargames_tournament.service.TournamentService;

import jakarta.websocket.server.PathParam;

@RestController
public class TournamentController {
	private static final String TOURNAMENT_SUCCESFULLY_CREATED = "Tournament succesfully created";
	private static final String TOURNAMENT_SUCCESFULLY_DELETED = "Tournament succesfully deleted";
	private static final String TOURNAMENT_SUCCESFULLY_UPDATED = "Tournament succesfully updated";

	@Autowired
	private TournamentService service;

	@GetMapping("/tournaments")
	public ResponseEntity<List<Tournament>> getTournaments() {
		return ResponseEntity.ofNullable(service.getTournaments());
	}

	@PostMapping("/tournaments/{id}")
	public ResponseEntity<Tournament> getTournament(@PathParam("id") long id) {
		try {
			return ResponseEntity.ofNullable(service.getTournament(id));
		} catch (TournamentNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PostMapping("/tournaments")
	public ResponseEntity<Object> insertTournament(@RequestBody Tournament tournament) {
		service.createTournament(tournament);
		return ResponseEntity.status(HttpStatus.CREATED).body(TOURNAMENT_SUCCESFULLY_CREATED);
	}

	@PutMapping("/tournaments/")
	public ResponseEntity<String> updateTournament(@RequestBody Tournament tournament) {
		try {
			service.updateTournament(tournament);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(TOURNAMENT_SUCCESFULLY_UPDATED);
		} catch (TournamentNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@DeleteMapping("/tournaments")
	public ResponseEntity<String> deleteTournament(@RequestBody Tournament tournament) {
		try {
			service.deleteTournament(tournament);
			return ResponseEntity.status(HttpStatus.OK).body(TOURNAMENT_SUCCESFULLY_DELETED);
		} catch (TournamentNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
