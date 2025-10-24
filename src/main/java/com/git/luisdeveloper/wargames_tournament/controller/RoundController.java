package com.git.luisdeveloper.wargames_tournament.controller;

import java.util.List;

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

	@Autowired
	private RoundService service;
	
	@GetMapping("/rounds/{roundId}/matches")
	public ResponseEntity<List<MatchDTO>> getMatches(@PathVariable Long roundId) throws RoundNotFoundException{
		return ResponseEntity.ofNullable(service.getMatches(roundId));
	}
	
	@PatchMapping("/rounds/{id}")
	public ResponseEntity<?> updateRoundDates(@RequestBody UpdateRoundDTO dto) throws RoundNotFoundException{
		service.updateDates(dto);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/rounds/{id}")
	public ResponseEntity<?> deleteRound(@PathVariable Long id) throws RoundNotFoundException{
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
