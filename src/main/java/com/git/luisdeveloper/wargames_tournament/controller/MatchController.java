package com.git.luisdeveloper.wargames_tournament.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.git.luisdeveloper.wargames_tournament.dto.PlayerRankingDTO;
import com.git.luisdeveloper.wargames_tournament.dto.UpdateMatchDTO;
import com.git.luisdeveloper.wargames_tournament.logging.ControllerLogFormatter;
import com.git.luisdeveloper.wargames_tournament.service.MatchService;
import com.git.luisdeveloper.wargames_tournament.service.PlayerService;

@RestController
public class MatchController {
	
	@Autowired
	private ControllerLogFormatter formatter;
	
	private static Logger logger = Logger.getLogger(MatchController.class.getName());
	
	@Autowired
	private MatchService service;
	
	@Autowired
	private PlayerService playerService;
	
	@PutMapping("/matches/results")
	public ResponseEntity<List<PlayerRankingDTO>> solveMatches(@RequestBody List<UpdateMatchDTO> matches){
		logger.info(formatter.request("Solving matches"));
		service.solveMatches(matches);
		logger.info(formatter.success("Solving matches"));
		logger.info(formatter.request("Getting updated ranking"));
		List<PlayerRankingDTO> ranking = playerService.getRanking();
		logger.info(formatter.success("Getting updated ranking"));
		return ResponseEntity.ofNullable(ranking);
	}
}
