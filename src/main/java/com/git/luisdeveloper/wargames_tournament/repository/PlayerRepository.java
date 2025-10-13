package com.git.luisdeveloper.wargames_tournament.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.git.luisdeveloper.wargames_tournament.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

	@Modifying
	@Query("UPDATE Player p SET p.points = p.points + :matchPoints WHERE p.id = :id ")
	int updatePlayerPoints(@Param("id") Long id, @Param("matchPoints") int points);

	@Modifying
	@Query("UPDATE Player p SET p.password = :newPassword WHERE p.id = :id AND p.password = :oldPassword")
	int updatePassword(@Param("id") Long id, @Param("oldPassword") String oldPassword,
			@Param("newPassword") String newPassword);
	
	@Modifying
	@Query("UPDATE Player p SET p.fullName = :name , p.email= :email WHERE p.id = :id")
	int updatePersonalData(@Param("id") Long id, @Param("name") String name,
			@Param("email") String email);
}
