package com.git.luisdeveloper.wargames_tournament.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.git.luisdeveloper.wargames_tournament.dto.TournamentRegistrationDTO;
import com.git.luisdeveloper.wargames_tournament.dto.TournamentSummaryDTO;
import com.git.luisdeveloper.wargames_tournament.entity.Tournament;

public class TournamentMapperTest {

	@BeforeEach
	void init() {
	}

	@Test
	void given_Tournament_when_invoking_toTournamentSummaryDTO_then_return_TournamentSummaryDTO_whose_fields_have_same_values_as_entity() {
		// given
		Tournament tournament = new Tournament(1L, "tournament", LocalDate.now(), LocalDate.now().plusDays(2L),
				"location", 55.5, 12.5);
		// when
		TournamentSummaryDTO result = TournamentMapper.toTournamentSummaryDTO(tournament);
		// then
		assertNotNull(result);
		assertEquals(tournament.getId(), result.id());
		assertEquals(tournament.getName(), result.name());
		assertEquals(tournament.getBeginDate(), result.beginDate());
		assertEquals(tournament.getEndDate(), result.endDate());
		assertEquals(tournament.getLocation(), result.location());
		assertEquals(tournament.getPrize(), result.prize());
		assertEquals(tournament.getEntryPrice(), result.entryPrice());

	}

	@Test
	void given_TournamentRegistrationDTO_when_invoking_toEntity_then_returns_dto_whose_fields_have_same_values_as_entity() {
		// given
		TournamentRegistrationDTO dto = new TournamentRegistrationDTO("tournament", LocalDate.now(),
				LocalDate.now().plusDays(2L), "location", 52.5, 2.5, new ArrayList<>());
		// when
		Tournament result = TournamentMapper.toEntity(dto);
		// then
		assertNotNull(result);
		assertEquals(dto.name(), result.getName());
		assertEquals(dto.beginDate(), result.getBeginDate());
		assertEquals(dto.endDate(), result.getEndDate());
		assertEquals(dto.location(), result.getLocation());
		assertEquals(dto.prize(), result.getPrize());
		assertEquals(dto.entryPrice(), result.getEntryPrice());
	}

}
