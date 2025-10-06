package com.git.luisdeveloper.wargames_tournament.service;

import java.util.List;

import com.git.luisdeveloper.wargames_tournament.dto.RoundDTO;
import com.git.luisdeveloper.wargames_tournament.dto.UpdateRoundDTO;
import com.git.luisdeveloper.wargames_tournament.entity.Player;
import com.git.luisdeveloper.wargames_tournament.exception.NoPendingRoundsException;

public interface RoundService {

	void delete(Long id);

	void updateDates(UpdateRoundDTO dto);
	
	RoundDTO generateMatches(Long tournamentId, List<Player>players) throws NoPendingRoundsException;
	
	RoundDTO findFirstPendingRoundDTO(Long tournamentId) throws NoPendingRoundsException;

}
