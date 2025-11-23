package com.git.luisdeveloper.wargames_tournament.controller;

import static com.git.luisdeveloper.wargames_tournament.logging.LogMessages.HTTP_DELETE;
import static com.git.luisdeveloper.wargames_tournament.logging.LogMessages.HTTP_GET;
import static com.git.luisdeveloper.wargames_tournament.logging.LogMessages.HTTP_PATCH;
import static com.git.luisdeveloper.wargames_tournament.logging.LogMessages.HTTP_POST;
import static com.git.luisdeveloper.wargames_tournament.logging.LogMessages.LEVEL_REQUEST;
import static com.git.luisdeveloper.wargames_tournament.logging.LogMessages.LEVEL_SUCCESS;

import java.util.List;
import java.util.logging.Logger;

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
import com.git.luisdeveloper.wargames_tournament.exception.InvalidCredentialsException;
import com.git.luisdeveloper.wargames_tournament.exception.PlayerNotFoundException;
import com.git.luisdeveloper.wargames_tournament.exception.TournamentNotFoundException;
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

	private static Logger logger = Logger.getLogger(PlayerController.class.getName());

	@GetMapping("/players/ranking")
	public ResponseEntity<List<PlayerRankingDTO>> getPlayers() {
		logger.info(()->LEVEL_REQUEST + HTTP_GET + "/players/ranking" + "- getting ranking");
		final List<PlayerRankingDTO> ranking = service.getPlayers();
		logger.info(()->LEVEL_SUCCESS + HTTP_GET + "/players/ranking" + " - getting ranking");
		return ResponseEntity.ofNullable(ranking);
	}

	@PostMapping("/players")
	public ResponseEntity<String> insertPlayer(@RequestBody PlayerRegistrationDTO player)
			throws TournamentNotFoundException {
		logger.info(()->LEVEL_REQUEST + HTTP_POST + "/players" + "- creating Player");
		tournamentService.addPlayer(player);
		logger.info(()->LEVEL_SUCCESS + HTTP_POST + "/players" + "- creating Player");
		return ResponseEntity.status(HttpStatus.CREATED).body(PLAYER_SUCCESFULLY_CREATED);
	}

	@PatchMapping("/players/personal-data")
	public ResponseEntity<String> updatePlayerPersonalData(@RequestBody UpdatePersonalDataDTO player)
			throws PlayerNotFoundException {
		logger.info(()->LEVEL_REQUEST + HTTP_PATCH + "/players/personal-data" + "- patching Player with id: "+player.playerId());
		service.updatePlayer(player);
		logger.info(()->LEVEL_SUCCESS + HTTP_PATCH + "/players/personal-data" + "- patching Player with id: "+player.playerId());
		return ResponseEntity.status(HttpStatus.OK).body(PLAYER_SUCCESFULLY_UPDATED);

	}

	@PatchMapping("/players/password")
	public ResponseEntity<String> updatePlayerPassword(@RequestBody UpdatePasswordDTO player)
			throws InvalidCredentialsException, PlayerNotFoundException {
		logger.info(()->LEVEL_REQUEST + HTTP_PATCH + "/players/password" + "- patching Player with id: "+player.playerId());
		service.updatePlayer(player);
		logger.info(()->LEVEL_SUCCESS + HTTP_PATCH + "/players/password" + "- patching Player with id: "+player.playerId());
		return ResponseEntity.status(HttpStatus.OK).body(PLAYER_SUCCESFULLY_UPDATED);
	}

	@DeleteMapping("/players/{id}")
	public ResponseEntity<String> deletePlayer(@PathVariable("id") Long id) throws PlayerNotFoundException {
		logger.info(()->LEVEL_REQUEST + HTTP_DELETE + "/players/" + "- deleting Player with id: "+id);
		service.deletePlayer(id);
		logger.info(()->LEVEL_SUCCESS + HTTP_DELETE + "/players/" + "- deleting Player with id: "+id);
		return ResponseEntity.status(HttpStatus.OK).body(PLAYER_SUCCESFULLY_DELETED);
	}
}
