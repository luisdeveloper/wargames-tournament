package com.git.luisdeveloper.wargames_tournament.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Round {
	@Id
	@GeneratedValue
	private Long id;
	private int roundNumber;

	@ManyToOne
	private Tournament tournament;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "round")
	private List<Match> matches;

	private LocalDate roundDate;

	private LocalTime beginTime, endTime;

	public Round() {
		this.matches = new ArrayList<>();
	}

	public Round(LocalDate roundDate, LocalTime beginTime, LocalTime endTime) {
		this();
		this.roundDate = roundDate;
		this.beginTime = beginTime.truncatedTo(ChronoUnit.MINUTES);
		this.endTime = endTime.truncatedTo(ChronoUnit.MINUTES);
	}
	
	public Round(int roundNumber, LocalDate roundDate, LocalTime beginTime, LocalTime endTime) {
		this(roundDate,beginTime, endTime);
		this.roundNumber = roundNumber;
	}

	public Round(Long id, int roundNumber, LocalDate roundDate, LocalTime beginTime, LocalTime endTime) {
		this(roundNumber,roundDate,beginTime, endTime);
		this.id = id;
	}

	

	public Round(Long id, int roundNumber, LocalDate roundDate, LocalTime beginTime, LocalTime endTime,
			List<Match> matches) {
		this(id, roundNumber,roundDate,beginTime, endTime);
		this.matches = matches;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getRoundNumber() {
		return roundNumber;
	}

	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}

	public List<Match> getMatches() {
		return matches;
	}

	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}

	public LocalDate getRoundDate() {
		return roundDate;
	}

	public void setRoundDate(LocalDate roundDate) {
		this.roundDate = roundDate;
	}

	public LocalTime getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(LocalTime beginTime) {
		this.beginTime = beginTime.truncatedTo(ChronoUnit.MINUTES);;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime.truncatedTo(ChronoUnit.MINUTES);;
	}

	public void addMatch(Match match) {
		matches.add(match);
		match.setRound(this);
	}
	
	public void addMatch(List<Match> matches) {
		matches.forEach(x->addMatch(x));
	}

	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}
	
	

}
