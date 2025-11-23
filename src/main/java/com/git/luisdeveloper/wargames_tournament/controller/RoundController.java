package com.git.luisdeveloper.wargames_tournament.controller;

import static com.git.luisdeveloper.wargames_tournament.logging.LogMessages.HTTP_DELETE;
import static com.git.luisdeveloper.wargames_tournament.logging.LogMessages.HTTP_GET;
import static com.git.luisdeveloper.wargames_tournament.logging.LogMessages.HTTP_PATCH;
import static com.git.luisdeveloper.wargames_tournament.logging.LogMessages.LEVEL_REQUEST;
import static com.git.luisdeveloper.wargames_tournament.logging.LogMessages.LEVEL_SUCCESS;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.git.luisdeveloper.wargames_tournament.dto.MatchDTO;
import com.git.luisdeveloper.wargames_tournament.dto.UpdateRoundDTO;
import com.git.luisdeveloper.wargames_tournament.exception.RoundNotFoundException;
import com.git.luisdeveloper.wargames_tournament.service.RoundService;

@RestController
public class RoundController {

	private static Logger logger = Logger.getLogger(RoundController.class.getName());

	@Autowired
	private RoundService service;

	@GetMapping("/rounds/{roundId}/matches")
	public ResponseEntity<List<MatchDTO>> getMatches(@PathVariable Long roundId) throws RoundNotFoundException {
		logger.info(() -> LEVEL_REQUEST + HTTP_GET + "/rounds/"+roundId+"/matches" + "- getting matches");
		final ResponseEntity<List<MatchDTO>> response = ResponseEntity.ofNullable(service.getMatches(roundId));
		logger.info(() -> LEVEL_SUCCESS + HTTP_GET + "/rounds/"+roundId+"/matches" + " - getting matches");
		return response;
	}

	@PatchMapping("/rounds/{id}")
	public ResponseEntity<?> updateRoundDates(@RequestBody UpdateRoundDTO dto) throws RoundNotFoundException {
		logger.info(() -> LEVEL_REQUEST + HTTP_PATCH + "/rounds/"+dto.roundId() + "- updating round dates");
		service.updateDates(dto);
		logger.info(() -> LEVEL_SUCCESS + HTTP_PATCH + "/rounds/"+dto.roundId() + " - updating round dates");
		
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/rounds/{id}")
	public ResponseEntity<?> deleteRound(@PathVariable Long id) throws RoundNotFoundException {
		logger.info(() -> LEVEL_REQUEST + HTTP_DELETE + "/rounds/"+id + "- deleting round");
		service.delete(id);
		logger.info(() -> LEVEL_SUCCESS + HTTP_DELETE + "/rounds/"+id + " - deleting round");
		
		
		return ResponseEntity.noContent().build();
	}
}
