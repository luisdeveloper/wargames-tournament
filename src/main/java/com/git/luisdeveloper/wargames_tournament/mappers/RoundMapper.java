package com.git.luisdeveloper.wargames_tournament.mappers;

import java.util.List;

import com.git.luisdeveloper.wargames_tournament.dto.MatchDTO;
import com.git.luisdeveloper.wargames_tournament.dto.RoundDTO;
import com.git.luisdeveloper.wargames_tournament.entity.Round;

public class RoundMapper {
	
	public static RoundDTO toDto(Round round) {
		List<MatchDTO> list = MatchMapper.toDto(round.getMatches());
		return new RoundDTO(round.getId(),round.getRoundNumber() , round.getRoundDate(), round.getBeginTime(), round.getEndTime(), list);
	}

}
