package com.git.luisdeveloper.wargames_tournament.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.git.luisdeveloper.wargames_tournament.dto.MatchDTO;
import com.git.luisdeveloper.wargames_tournament.entity.Match;
import com.git.luisdeveloper.wargames_tournament.entity.Player;
import com.git.luisdeveloper.wargames_tournament.enums.MatchResult;

@ExtendWith(MockitoExtension.class)
public class MatchMapperTest {

	


	@Test
	void given_list_of_Match_when_invoking_toDto_then_returns_list_of_MatchDTO_with_same_number_of_elements() {
		// given
		List<Match> matches = new ArrayList<>();
		matches.add(new Match(new Player(),new Player()));
		matches.add(new Match(new Player(),new Player()));
		// when
		List<MatchDTO> result = MatchMapper.toDto(matches);
		// then
		assertNotNull(result);
		assertEquals(matches.size(), result.size());

	}

	@Test
	void given_Match_when_invoking_toDto_then_returns_MatchDTO_whose_fields_have_same_values_as_entity() {
		// given
		Player player1 = new Player(1L, "player1", "player1@email", 3);
		Player player2 = new Player(2L, "player2", "player2@email", 13);
		Match match = new Match(12L, player1, player2, MatchResult.PLAYER_1_VICTORY);

		// when
		MatchDTO result = MatchMapper.toDto(match);
		// then
		assertNotNull(result);
		assertNotNull(result.player1());
		assertNotNull(result.player2());
		assertEquals(match.getResult(), result.result());
	}

}
