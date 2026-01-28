package com.git.luisdeveloper.wargames_tournament.service.impl;

import java.util.List;

import com.git.luisdeveloper.wargames_tournament.dto.MatchDTO;
import com.git.luisdeveloper.wargames_tournament.enums.MatchResult;

interface MatchInternalService {
	void solveMatch(Long matchId, Long player1Id, Long player2Id, MatchResult result);
	
	List<MatchDTO> getMatches(Long roundId);

}
