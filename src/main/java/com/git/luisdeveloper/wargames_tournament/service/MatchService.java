package com.git.luisdeveloper.wargames_tournament.service;

import java.util.List;

import com.git.luisdeveloper.wargames_tournament.dto.MatchDTO;
import com.git.luisdeveloper.wargames_tournament.dto.UpdateMatchDTO;

public interface MatchService {
	void solveMatches(List<UpdateMatchDTO> matches);

	List<MatchDTO> getMatches(Long roundId);

	void solveMatch(UpdateMatchDTO match);

	

}
