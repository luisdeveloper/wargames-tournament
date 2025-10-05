package com.git.luisdeveloper.wargames_tournament.dto;

import com.git.luisdeveloper.wargames_tournament.enums.MatchResult;

import jakarta.validation.constraints.NotNull;

public record UpdateMatchDTO (@NotNull Long matchId, Long player1Id, Long player2Id, MatchResult result){

}
