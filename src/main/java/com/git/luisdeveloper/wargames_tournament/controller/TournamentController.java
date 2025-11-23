package com.git.luisdeveloper.wargames_tournament.controller;

import static com.git.luisdeveloper.wargames_tournament.logging.LogMessages.HTTP_DELETE;
import static com.git.luisdeveloper.wargames_tournament.logging.LogMessages.HTTP_GET;
import static com.git.luisdeveloper.wargames_tournament.logging.LogMessages.HTTP_POST;
import static com.git.luisdeveloper.wargames_tournament.logging.LogMessages.LEVEL_REQUEST;
import static com.git.luisdeveloper.wargames_tournament.logging.LogMessages.LEVEL_SUCCESS;

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
import com.git.luisdeveloper.wargames_tournament.service.TournamentService;

@RestController
public class TournamentController {
	private static final String TOURNAMENT_SUCCESFULLY_DELETED = "Tournament succesfully deleted";

	@Autowired
	private TournamentService service;

	private static Logger logger = Logger.getLogger(TournamentController.class.getName());

	@GetMapping("/tournaments/{id}/summary")
	public ResponseEntity<TournamentSummaryDTO> getTournamentSummary(@PathVariable long id)
			throws TournamentNotFoundException {
		logger.info(
				() -> LEVEL_REQUEST + HTTP_GET + "/tournaments/" + id + "/summary" + "- getting tournament summary");
		final TournamentSummaryDTO tournamentSummaryDto = service.getTournament(id);
		logger.info(
				() -> LEVEL_SUCCESS + HTTP_GET + "/tournaments/" + id + "/summary" + " - getting tournament summary");
		return ResponseEntity.ofNullable(tournamentSummaryDto);
	}

	@GetMapping("/tournaments/{id}/current-round")
	public ResponseEntity<RoundDTO> getCurrentRound(@PathVariable Long id)
			throws TournamentNotFoundException, NoPendingRoundsException {
		logger.info(
				() -> LEVEL_REQUEST + HTTP_GET + "/tournaments/" + id + "/current-round" + "- getting current round");
		final RoundDTO currentRound = service.getCurrentRound(id);
		logger.info(
				() -> LEVEL_SUCCESS + HTTP_GET + "/tournaments/" + id + "/current-round" + " - getting current round");
		return ResponseEntity.ofNullable(currentRound);
	}

	@PostMapping("/tournaments")
	public ResponseEntity<TournamentSummaryDTO> insertTournament(@RequestBody TournamentRegistrationDTO tournament) {
		logger.info(() -> LEVEL_REQUEST + HTTP_POST + "/tournaments/" + "- creating a new tournament");
		final TournamentSummaryDTO dto = service.createTournament(tournament);
		logger.info(() -> LEVEL_SUCCESS + HTTP_POST + "/tournaments/" + " - creating new tournament");
		return ResponseEntity.ofNullable(dto);
	}

	@PostMapping("/tournaments/{tournamentId}/current-round/matches")
	public ResponseEntity<RoundDTO> generateMatches(@PathVariable Long tournamentId)
			throws TournamentNotFoundException, RoundAlreadyStartedException, NoPendingRoundsException {
		logger.info(() -> LEVEL_REQUEST + HTTP_POST + "/tournaments/" + tournamentId + "/current-round/matches"
				+ "- generating matches");
		RoundDTO dto = service.generateMatches(tournamentId);
		logger.info(() -> LEVEL_SUCCESS + HTTP_POST + "/tournaments/" + tournamentId + "/current-round/matches"
				+ " - generating matches");
		return ResponseEntity.ofNullable(dto);
	}

	@DeleteMapping("/tournaments/{id}")
	public ResponseEntity<String> deleteTournament(@PathVariable Long id) throws TournamentNotFoundException {

		logger.info(() -> LEVEL_REQUEST + HTTP_DELETE + "/tournaments/" + id + "- deleting tournament");
		service.deleteTournament(id);
		logger.info(() -> LEVEL_SUCCESS + HTTP_DELETE + "/tournaments/" + id + " - deleting tournament");
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(TOURNAMENT_SUCCESFULLY_DELETED);
	}
}
