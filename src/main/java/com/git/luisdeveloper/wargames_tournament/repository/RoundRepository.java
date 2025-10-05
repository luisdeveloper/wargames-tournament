package com.git.luisdeveloper.wargames_tournament.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.git.luisdeveloper.wargames_tournament.entity.Round;

public interface RoundRepository extends JpaRepository<Round, Long> {

	@Modifying
	@Query("UPDATE Round r SET r.roundDate = :roundDate, r.beginTime = :begin, r.endTime = :end  WHERE r.id = :id")
	int updateDates(@Param("id") Long id, @Param("roundDate") LocalDate roundDate,@Param("begin") LocalTime beginTime, @Param("end") LocalTime endTime);

	@Query("SELECT r FROM Round r WHERE r.tournament.id = :tournamentId AND r.matches IS EMPTY ORDER BY r.roundDate ASC, r.beginTime ASC")
	Optional<Round> findFirstPendingRound(@Param("tournamentId")Long tournamentId);
}
