package com.git.luisdeveloper.wargames_tournament.dto;

public record PlayerRankingDTO (String name, String email, int points) implements Comparable<PlayerRankingDTO>{

	@Override
	public int compareTo(PlayerRankingDTO o) {
		return this.points - o.points;
	}

}
