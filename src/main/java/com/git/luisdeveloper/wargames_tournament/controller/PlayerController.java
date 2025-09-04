package com.git.luisdeveloper.wargames_tournament.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.git.luisdeveloper.wargames_tournament.entity.Player;
import com.git.luisdeveloper.wargames_tournament.exception.PlayerNotFoundException;
import com.git.luisdeveloper.wargames_tournament.service.PlayerService;

import jakarta.websocket.server.PathParam;

@RestController
public class PlayerController {
	
	private static final String PLAYER_SUCCESFULLY_CREATED = "Player succesfully created";
	private static final String PLAYER_SUCCESFULLY_UPDATED = "Player succesfully updated";
	private static final String PLAYER_SUCCESFULLY_DELETED = "Player succesfully deleted";
	@Autowired
	private PlayerService service;
	
	@GetMapping("/players")
	public ResponseEntity<List<Player>> getPlayers(){
		return ResponseEntity.ofNullable(service.getPlayers());
	}
	
	@PostMapping("/players/{id}")
	public ResponseEntity<Player> getPlayer(@PathParam("id")long id){
		try {
			return ResponseEntity.ofNullable(service.getPlayer(id));
		} catch (PlayerNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PostMapping("/players")
	public ResponseEntity<String> insertPlayer(@RequestBody Player player) {
		service.createPlayer(player);
		return ResponseEntity.status(HttpStatus.CREATED).body(PLAYER_SUCCESFULLY_CREATED);
	}
	
	@PutMapping("/players/")
	public ResponseEntity<String> updatePlayer(@RequestBody Player player) {
		try {
			service.updatePlayer(player);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(PLAYER_SUCCESFULLY_UPDATED);
		} catch (PlayerNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/players")
	public ResponseEntity<String> deletePlayer(@RequestBody Player player) {
		try {
			service.deletePlayer(player);
			return ResponseEntity.status(HttpStatus.OK).body(PLAYER_SUCCESFULLY_DELETED);
		} catch (PlayerNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
