package com.git.luisdeveloper.wargames_tournament.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.git.luisdeveloper.wargames_tournament.entity.Match;
import com.git.luisdeveloper.wargames_tournament.enums.MatchResult;

public interface MatchRepository extends JpaRepository<Match, Long> {
	List<Match> findAllByRound_Id(Long id);
	
	@Modifying
	@Query("UPDATE Match m SET m.result = :result WHERE m.id = :matchId ")
	int updateMatchResult(@Param("matchId") Long matchId, @Param("result") MatchResult result);
	
	@Modifying
	@Query("UPDATE Match m SET m.result = :result WHERE m.id IN :ids ")
	int updateMatchResults(@Param("ids") List<Long> matchIds, @Param("result") MatchResult result);
	
	
	
}
