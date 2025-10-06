package com.git.luisdeveloper.wargames_tournament.service;

import java.util.List;

import com.git.luisdeveloper.wargames_tournament.dto.PlayerRankingDTO;
import com.git.luisdeveloper.wargames_tournament.dto.UpdatePasswordDTO;
import com.git.luisdeveloper.wargames_tournament.dto.UpdatePersonalDataDTO;
import com.git.luisdeveloper.wargames_tournament.exception.InvalidCredentialsException;
import com.git.luisdeveloper.wargames_tournament.exception.PlayerNotFoundException;

public interface PlayerService {
	List<PlayerRankingDTO> getPlayers();

	void updatePlayer(UpdatePersonalDataDTO player) throws PlayerNotFoundException;

	void updatePlayer(UpdatePasswordDTO player) throws InvalidCredentialsException;

	void deletePlayer(Long id) throws PlayerNotFoundException;

	List<PlayerRankingDTO> getRanking();
	
	int updatePlayerPoints(Long id, int points);

}
