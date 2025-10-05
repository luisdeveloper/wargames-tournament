package com.git.luisdeveloper.wargames_tournament.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.git.luisdeveloper.wargames_tournament.dto.PlayerRankingDTO;
import com.git.luisdeveloper.wargames_tournament.dto.UpdateMatchDTO;
import com.git.luisdeveloper.wargames_tournament.service.MatchService;
import com.git.luisdeveloper.wargames_tournament.service.PlayerService;

@RestController
public class MatchController {
	
	@Autowired
	private MatchService service;
	
	@Autowired
	private PlayerService playerService;
	
	@PutMapping("/matches/results")
	public ResponseEntity<List<PlayerRankingDTO>> solveMatches(List<UpdateMatchDTO> matches){
		service.solveMatches(matches);
		List<PlayerRankingDTO> ranking = playerService.getRanking();
		return ResponseEntity.ofNullable(ranking);
	}
}
