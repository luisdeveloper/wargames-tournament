package com.git.luisdeveloper.wargames_tournament.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.git.luisdeveloper.wargames_tournament.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>{

}
