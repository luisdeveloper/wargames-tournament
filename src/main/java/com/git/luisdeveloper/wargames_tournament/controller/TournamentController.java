package com.git.luisdeveloper.wargames_tournament.controller;

import java.util.logging.Logger;

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
import com.git.luisdeveloper.wargames_tournament.logging.ControllerLogFormatter;
import com.git.luisdeveloper.wargames_tournament.service.TournamentService;

@RestController
public class TournamentController {
	private static final String TOURNAMENT_SUCCESFULLY_DELETED = "Tournament succesfully deleted";

	@Autowired
	private TournamentService service;
	
	@Autowired
	private ControllerLogFormatter formatter;

	private static Logger logger = Logger.getLogger(TournamentController.class.getName());

	@GetMapping("/tournaments/{id}/summary")
	public ResponseEntity<TournamentSummaryDTO> getTournamentSummary(@PathVariable long id)
			throws TournamentNotFoundException {
		logger.info(formatter.request("Getting tournament summary"));
		final TournamentSummaryDTO tournamentSummaryDto = service.getTournament(id);
		logger.info(formatter.success("Getting tournament summary"));
		return ResponseEntity.ofNullable(tournamentSummaryDto);
	}

	@GetMapping("/tournaments/{id}/current-round")
	public ResponseEntity<RoundDTO> getCurrentRound(@PathVariable Long id)
			throws TournamentNotFoundException, NoPendingRoundsException {
		logger.info(formatter.request("Getting current round"));
		final RoundDTO currentRound = service.getCurrentRound(id);
		logger.info(formatter.success("Getting current round"));
		return ResponseEntity.ofNullable(currentRound);
	}

	@PostMapping("/tournaments")
	public ResponseEntity<TournamentSummaryDTO> insertTournament(@RequestBody TournamentRegistrationDTO tournament) {
		logger.info(formatter.request("Creating a new tournament"));
		final TournamentSummaryDTO dto = service.createTournament(tournament);
		logger.info(formatter.success("Creating a new tournament"));
		return ResponseEntity.ofNullable(dto);
	}

	@PostMapping("/tournaments/{tournamentId}/current-round/matches")
	public ResponseEntity<RoundDTO> generateMatches(@PathVariable Long tournamentId)
			throws TournamentNotFoundException, RoundAlreadyStartedException, NoPendingRoundsException {
		logger.info(formatter.request("Generating matches"));
		RoundDTO dto = service.generateMatches(tournamentId);
		logger.info(formatter.success("Generating matches"));
		return ResponseEntity.ofNullable(dto);
	}

	@DeleteMapping("/tournaments/{id}")
	public ResponseEntity<String> deleteTournament(@PathVariable Long id) throws TournamentNotFoundException {
		logger.info(formatter.request("Deleting tournament"));
		service.deleteTournament(id);
		logger.info(formatter.success("Deleting tournament"));
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(TOURNAMENT_SUCCESFULLY_DELETED);
	}
}
