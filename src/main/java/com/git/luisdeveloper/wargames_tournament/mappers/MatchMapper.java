package com.git.luisdeveloper.wargames_tournament.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.git.luisdeveloper.wargames_tournament.dto.MatchDTO;
import com.git.luisdeveloper.wargames_tournament.dto.PlayerRankingDTO;
import com.git.luisdeveloper.wargames_tournament.entity.Match;
import com.git.luisdeveloper.wargames_tournament.enums.MatchResult;

@Component
public class MatchMapper {

	public static MatchDTO toDto(Match match) {
		PlayerRankingDTO player2 = (match.getResult() != MatchResult.BYE)
				? PlayerMapper.toPlayerRankingDTO(match.getPlayer2())
				: null;
		return new MatchDTO(match.getId(), PlayerMapper.toPlayerRankingDTO(match.getPlayer1()), player2,
				match.getResult());
	}

	public static List<MatchDTO> toDto(List<Match> matches) {
		return matches.stream().map(x -> toDto(x)).toList();
	}
}
