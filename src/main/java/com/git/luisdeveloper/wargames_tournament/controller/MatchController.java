package com.git.luisdeveloper.wargames_tournament.controller;

import static com.git.luisdeveloper.wargames_tournament.logging.LogMessages.HTTP_PUT;
import static com.git.luisdeveloper.wargames_tournament.logging.LogMessages.LEVEL_REQUEST;
import static com.git.luisdeveloper.wargames_tournament.logging.LogMessages.LEVEL_SUCCESS;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.git.luisdeveloper.wargames_tournament.dto.PlayerRankingDTO;
import com.git.luisdeveloper.wargames_tournament.dto.UpdateMatchDTO;
import com.git.luisdeveloper.wargames_tournament.service.MatchService;
import com.git.luisdeveloper.wargames_tournament.service.PlayerService;

@RestController
public class MatchController {
	
	private static Logger logger = Logger.getLogger(MatchController.class.getName());
	
	@Autowired
	private MatchService service;
	
	@Autowired
	private PlayerService playerService;
	
	@PutMapping("/matches/results")
	public ResponseEntity<List<PlayerRankingDTO>> solveMatches(@RequestBody List<UpdateMatchDTO> matches){
		logger.info(()->LEVEL_REQUEST + HTTP_PUT + "/matches/results" + "- solving matches");
		service.solveMatches(matches);
		logger.info(()->LEVEL_SUCCESS + HTTP_PUT + "/matches/results" + " - solving matches");
		List<PlayerRankingDTO> ranking = playerService.getRanking();
		return ResponseEntity.ofNullable(ranking);
	}
}
