package com.git.luisdeveloper.wargames_tournament.exception;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.git.luisdeveloper.wargames_tournament.dto.RoundDTO;
import com.git.luisdeveloper.wargames_tournament.entity.Player;
import com.git.luisdeveloper.wargames_tournament.logging.ControllerLogFormatter;

@ControllerAdvice
public class ExceptionsHandler {

	@Autowired
	private ControllerLogFormatter formatter;

	private static Logger logger = Logger.getLogger(ExceptionsHandler.class.getName());

	@ExceptionHandler(PlayerNotFoundException.class)
	public ResponseEntity<Player> handlePlayerNotFoundException(PlayerNotFoundException ex) {
		logger.warning(formatter.error("PlayerNotFoundException: Player could not be found"));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<Player> handleInvalidCredentialsException(InvalidCredentialsException ex) {
		logger.warning(formatter.error("InvalidCredentialsException: Invalid credentials for requested user"));
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	@ExceptionHandler(TournamentNotFoundException.class)
	public ResponseEntity<Player> handleTournamentNotFoundException(TournamentNotFoundException ex) {
		logger.warning(formatter.error("TournamentNotFoundException: Tournament could not be found"));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@ExceptionHandler(NoPendingRoundsException.class)
	public ResponseEntity<RoundDTO> handleNoPendingRoundsException(NoPendingRoundsException ex) {
		logger.info(
				formatter.warning("NoPendingRoundsException: There are no remaining rounds. Tournament is finished"));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@ExceptionHandler(RoundNotFoundException.class)
	public ResponseEntity<RoundDTO> handleRoundNotFoundException(RoundNotFoundException ex) {
		logger.warning(formatter.error("RoundNotFoundException: Round could not be found"));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleRuntimeException(RuntimeException ex) {
		logger.severe(formatter.error("RuntimeException: " + ex.getMessage()));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

}
