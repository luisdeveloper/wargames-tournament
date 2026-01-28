package com.git.luisdeveloper.wargames_tournament.controller;

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
import com.git.luisdeveloper.wargames_tournament.logging.ControllerLogFormatter;
import com.git.luisdeveloper.wargames_tournament.service.RoundService;

@RestController
public class RoundController {

	@Autowired
	private ControllerLogFormatter formatter;

	private static Logger logger = Logger.getLogger(RoundController.class.getName());

	@Autowired
	private RoundService service;

	@GetMapping("/rounds/{roundId}/matches")
	public ResponseEntity<List<MatchDTO>> getMatches(@PathVariable Long roundId) throws RoundNotFoundException {
		logger.info(formatter.request("Getting matches"));
		final ResponseEntity<List<MatchDTO>> response = ResponseEntity.ofNullable(service.getMatches(roundId));
		logger.info(formatter.success("Getting matches"));
		return response;
	}

	@PatchMapping("/rounds/{id}")
	public ResponseEntity<?> updateRoundDates(@RequestBody UpdateRoundDTO dto) throws RoundNotFoundException {
		logger.info(formatter.request("Updating round dates"));
		service.updateDates(dto);
		logger.info(formatter.success("Updating round dates"));
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/rounds/{id}")
	public ResponseEntity<?> deleteRound(@PathVariable Long id) throws RoundNotFoundException {
		logger.info(formatter.request("Deleting round"));
		service.delete(id);
		logger.info(formatter.success("Deleting round"));
		return ResponseEntity.noContent().build();
	}
}
