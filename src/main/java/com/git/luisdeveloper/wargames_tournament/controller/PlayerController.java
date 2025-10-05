package com.git.luisdeveloper.wargames_tournament.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.git.luisdeveloper.wargames_tournament.dto.PlayerRankingDTO;
import com.git.luisdeveloper.wargames_tournament.dto.PlayerRegistrationDTO;
import com.git.luisdeveloper.wargames_tournament.dto.UpdatePasswordDTO;
import com.git.luisdeveloper.wargames_tournament.dto.UpdatePersonalDataDTO;
import com.git.luisdeveloper.wargames_tournament.exception.PlayerNotFoundException;
import com.git.luisdeveloper.wargames_tournament.exception.TournamentNotFoundException;
import com.git.luisdeveloper.wargames_tournament.exceptions.InvalidCredentialsException;
import com.git.luisdeveloper.wargames_tournament.service.PlayerService;
import com.git.luisdeveloper.wargames_tournament.service.TournamentService;

@RestController
public class PlayerController {
	
	private static final String PLAYER_SUCCESFULLY_CREATED = "Player succesfully created";
	private static final String PLAYER_SUCCESFULLY_UPDATED = "Player succesfully updated";
	private static final String PLAYER_SUCCESFULLY_DELETED = "Player succesfully deleted";
	
	@Autowired
	private PlayerService service;
	
	@Autowired
	private TournamentService tournamentService;
	
	@GetMapping("/players/ranking")
	public ResponseEntity<List<PlayerRankingDTO>> getPlayers(){
		return ResponseEntity.ofNullable(service.getPlayers());
	}
	
	@PostMapping("/players")
	public ResponseEntity<String> insertPlayer(@RequestBody PlayerRegistrationDTO player) throws TournamentNotFoundException {
		tournamentService.addPlayer(player);
		return ResponseEntity.status(HttpStatus.CREATED).body(PLAYER_SUCCESFULLY_CREATED);
	}
	
	@PatchMapping("/players/personal-data")
	public ResponseEntity<String> updatePlayerPersonalData(@RequestBody UpdatePersonalDataDTO player) {
		try {
			service.updatePlayer(player);
			return ResponseEntity.status(HttpStatus.OK).body(PLAYER_SUCCESFULLY_UPDATED);
		} catch (PlayerNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PatchMapping("/players/password")
	public ResponseEntity<String> updatePlayerPassword(@RequestBody UpdatePasswordDTO player) throws InvalidCredentialsException {
		service.updatePlayer(player);
		return ResponseEntity.status(HttpStatus.OK).body(PLAYER_SUCCESFULLY_UPDATED);
	}
	
	@DeleteMapping("/players/{id}")
	public ResponseEntity<String> deletePlayer(@PathVariable("id") Long id) {
		try {
			service.deletePlayer(id);
			return ResponseEntity.status(HttpStatus.OK).body(PLAYER_SUCCESFULLY_DELETED);
		} catch (PlayerNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
