package com.git.luisdeveloper.wargames_tournament.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.git.luisdeveloper.wargames_tournament.entity.Match;

public interface MatchRepository extends JpaRepository<Match, Long> {
	
}
