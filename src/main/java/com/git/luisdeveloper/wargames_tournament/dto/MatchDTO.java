package com.git.luisdeveloper.wargames_tournament.dto;

import com.git.luisdeveloper.wargames_tournament.enums.MatchResult;

public record MatchDTO(Long matchId, PlayerRankingDTO player1, PlayerRankingDTO player2, MatchResult result) {

}
