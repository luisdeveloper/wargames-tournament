package com.git.luisdeveloper.wargames_tournament.entity;

import com.git.luisdeveloper.wargames_tournament.enums.MatchResult;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Match {
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private Round round;

	@OneToOne
	private Player player1;

	@OneToOne
	private Player player2;

	@Enumerated(EnumType.STRING)
	private MatchResult result;

	public Match() {
	}

	public Match(Player player1, Player player2) {
		super();
		this.player1 = player1;
		this.player2 = player2;
	}

	public Match(Player player1, Player player2, MatchResult result) {
		super();
		this.player1 = player1;
		this.player2 = player2;
		this.result = result;
	}

	public Match(Long id, Player player1, Player player2, MatchResult result) {
		super();
		this.id = id;
		this.player1 = player1;
		this.player2 = player2;
		this.result = result;
	}

	public Match(long matchId, MatchResult result) {
		this.id = matchId;
		this.result = result;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public MatchResult getResult() {
		return result;
	}

	public void setResult(MatchResult result) {
		this.result = result;
	}

}
