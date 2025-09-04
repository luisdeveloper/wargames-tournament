package com.git.luisdeveloper.wargames_tournament.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.git.luisdeveloper.wargames_tournament.entity.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament, Long>{

}
