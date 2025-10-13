package com.git.luisdeveloper.wargames_tournament.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.git.luisdeveloper.wargames_tournament.dto.RoundDTO;
import com.git.luisdeveloper.wargames_tournament.entity.Match;
import com.git.luisdeveloper.wargames_tournament.entity.Round;

@ExtendWith(MockitoExtension.class)
public class RoundMapperTest {

	@Test
	void given_Round_when_invoking_toDto_then_returns_MatchDTO_whose_fields_have_same_values_as_entity() {
		// given
		Round round = new Round(12L, 2, LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(2L),
				new ArrayList<Match>());
		// when
		RoundDTO result = RoundMapper.toDto(round);
		// then
		assertNotNull(result);
		assertEquals(round.getId(), result.roundId());
		assertEquals(round.getRoundNumber(), result.roundNumber());
		assertEquals(round.getRoundDate(), result.roundDate());
		assertEquals(round.getBeginTime(), result.beginTime());
		assertEquals(round.getEndTime(), result.endTime());
		assertNotNull(round.getMatches());
	}
}
