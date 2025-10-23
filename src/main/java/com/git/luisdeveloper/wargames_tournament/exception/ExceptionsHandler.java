package com.git.luisdeveloper.wargames_tournament.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.git.luisdeveloper.wargames_tournament.dto.RoundDTO;
import com.git.luisdeveloper.wargames_tournament.entity.Player;

@ControllerAdvice
public class ExceptionsHandler {
	
	@ExceptionHandler(PlayerNotFoundException.class)
	public ResponseEntity<Player> handlePlayerNotFoundException(){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@ExceptionHandler(TournamentNotFoundException.class)
	public ResponseEntity<Player> handleTournamentNotFoundException(){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@ExceptionHandler(NoPendingRoundsException.class)
	public ResponseEntity<RoundDTO> handleNoPendingRoundsException(){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@ExceptionHandler(RoundNotFoundException.class)
	public ResponseEntity<RoundDTO> handleRoundNotFoundException(){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
}
