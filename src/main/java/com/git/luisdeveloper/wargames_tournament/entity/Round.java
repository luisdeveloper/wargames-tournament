package com.git.luisdeveloper.wargames_tournament.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Round {
	@Id
	@GeneratedValue
	private Long id;
	private int roundNumber;
	
	@OneToMany
	private List<Match> matches;
	
	private LocalDate roundDate;
	
	private LocalTime beginTime, endTime;
	
	public Round() {
		
	}
	
	public Round(int roundNumber, LocalDate roundDate, LocalTime beginTime, LocalTime endTime) {
		this.roundNumber = roundNumber;
		this.roundDate = roundDate;
		this.beginTime = beginTime;
		this.endTime = endTime;
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
		this.beginTime = beginTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	
	
}
