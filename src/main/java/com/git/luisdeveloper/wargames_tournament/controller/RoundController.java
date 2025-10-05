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
import com.git.luisdeveloper.wargames_tournament.service.MatchService;
import com.git.luisdeveloper.wargames_tournament.service.RoundService;

@RestController
public class RoundController {

	@Autowired
	private MatchService matchService;
	
	@Autowired
	private RoundService service;
	
	@GetMapping("/rounds/{roundId}/matches")
	public ResponseEntity<List<MatchDTO>> getMatches(@PathVariable Long roundId){
		return ResponseEntity.ofNullable(matchService.getMatches(roundId));
	}
	
	@PatchMapping("/rounds/{id}")
	public ResponseEntity<?> updateRoundDates(@RequestBody UpdateRoundDTO dto){
		service.updateDates(dto);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/rounds/{id}")
	public ResponseEntity<?> deleteRound(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
