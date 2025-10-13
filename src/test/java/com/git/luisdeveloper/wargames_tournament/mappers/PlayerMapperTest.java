package com.git.luisdeveloper.wargames_tournament.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.git.luisdeveloper.wargames_tournament.dto.PlayerRankingDTO;
import com.git.luisdeveloper.wargames_tournament.dto.PlayerRegistrationDTO;
import com.git.luisdeveloper.wargames_tournament.entity.Player;

public class PlayerMapperTest {

	@Test
	void given_PlayerRegistrationDTO_whenInvoking_toEntity_thenReturns_Player_whose_fields_have_same_values_as_dto() {
		// given
		PlayerRegistrationDTO dto = new PlayerRegistrationDTO(1L, "name", "name@email", "password");
		// when
		Player result = PlayerMapper.toEntity(dto);
		// then
		assertNotNull(result);
		assertEquals(dto.name(), result.getFullName());
		assertEquals(dto.email(), result.getEmail());
		assertEquals(dto.password(), result.getPassword());
	}

	@Test
	void given_Player_whenInvoking_toPlayerRankingDTO_thenReturns_Player_whose_fields_have_same_values_as_dto() {
		// given
		Player player = new Player(1L, "name", "name@email", 12);
		// when
		PlayerRankingDTO result = PlayerMapper.toPlayerRankingDTO(player);
		// then
		assertNotNull(result);
		assertEquals(player.getFullName(), result.name());
		assertEquals(player.getEmail(), result.email());
		assertEquals(player.getPoints(), result.points());
	}
}
