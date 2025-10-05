package com.git.luisdeveloper.wargames_tournament.mappers;

import com.git.luisdeveloper.wargames_tournament.dto.TournamentRegistrationDTO;
import com.git.luisdeveloper.wargames_tournament.dto.TournamentSummaryDTO;
import com.git.luisdeveloper.wargames_tournament.entity.Tournament;

public class TournamentMapper {

	public static TournamentSummaryDTO toTournamentSummaryDTO(Tournament tournament) {
		return new TournamentSummaryDTO(tournament.getId(), tournament.getName(), tournament.getBeginDate(), tournament.getEndDate(),
				tournament.getPrize(), tournament.getEntryPrice(), tournament.getLocation());
	}

	public static Tournament toEntity(TournamentRegistrationDTO tournamentRegistrationDTO) {
		return new Tournament(null, tournamentRegistrationDTO.name(), tournamentRegistrationDTO.beginDate(),
				tournamentRegistrationDTO.endDate(), tournamentRegistrationDTO.location(),
				tournamentRegistrationDTO.prize(),tournamentRegistrationDTO.entryPrice());
	}

}
