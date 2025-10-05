package com.git.luisdeveloper.wargames_tournament.service.impl;

import com.git.luisdeveloper.wargames_tournament.enums.MatchResult;

interface MatchInternalService {
	void solveMatch(Long matchId, Long player1Id, Long player2Id, MatchResult result);

}
