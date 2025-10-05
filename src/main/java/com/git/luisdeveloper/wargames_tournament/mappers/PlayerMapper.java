package com.git.luisdeveloper.wargames_tournament.mappers;

import com.git.luisdeveloper.wargames_tournament.dto.PlayerRankingDTO;
import com.git.luisdeveloper.wargames_tournament.dto.PlayerRegistrationDTO;
import com.git.luisdeveloper.wargames_tournament.entity.Player;

public class PlayerMapper {
	public static Player toEntity(PlayerRegistrationDTO dto) {
		return new Player(dto.name(), dto.email(), dto.password());
	}

	public static PlayerRankingDTO toPlayerRankingDTO(Player player) {
		return new PlayerRankingDTO(player.getFullName(), player.getEmail(), player.getPoints());
	}
}
