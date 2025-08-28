package com.git.luisdeveloper.wargames_tournament.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.git.luisdeveloper.wargames_tournament.entity.Player;
import com.git.luisdeveloper.wargames_tournament.service.PlayerService;

@RestController
public class PlayerController {
	
	@Autowired
	private PlayerService service;
	
	@GetMapping("/players")
	public List<Player> getPlayers(){
		return service.getPlayers();
	}
}
