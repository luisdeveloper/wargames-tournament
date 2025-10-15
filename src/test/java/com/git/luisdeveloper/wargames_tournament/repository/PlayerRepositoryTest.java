package com.git.luisdeveloper.wargames_tournament.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.git.luisdeveloper.wargames_tournament.entity.Player;

@DataJpaTest
@ActiveProfiles("test")
public class PlayerRepositoryTest {

	@Autowired
	private PlayerRepository repository;

	@Test
	void given_new_player_when_save_then_player_persisted_in_db() {
		// given
		Player p = new Player("name", "email", "");
		// when
		Player result = repository.save(p);

		// then
		assertNotNull(result);
		assertEquals(p.getFullName(), result.getFullName());
		assertEquals(p.getEmail(), result.getEmail());
	}

	@Test
	void given_player_stored_in_db_when_findById_then_returns_player() {
		// given
		Player p = new Player("name", "email", "");
		p = repository.save(p);
		// when
		Optional<Player> result = repository.findById(p.getId());

		// then
		assertNotNull(result);
		assertTrue(result.isPresent());
		assertEquals(p.getFullName(), result.get().getFullName());
		assertEquals(p.getEmail(), result.get().getEmail());
	}

	@Test
	void given_player_stored_in_db_when_update_then_returns_player_updated() {
		// given
		Player p = new Player("name", "email", "");
		p = repository.save(p);
		Player playerUpdated = new Player(p.getId(), "new name", "new email", 12);
		playerUpdated.setPassword(p.getPassword());
		// when
		Player result = repository.save(playerUpdated);

		// then
		assertNotNull(result);
		assertEquals(playerUpdated.getFullName(), result.getFullName());
		assertEquals(playerUpdated.getEmail(), result.getEmail());
		assertEquals(playerUpdated.getPoints(), result.getPoints());
	}

	@Test
	void given_player_stored_in_db_when_delete_then_player_is_no_longer_in_db() {
		// given
		Player p = new Player("name", "email", "");
		p = repository.save(p);
		// when
		repository.delete(p);

		// then
		Optional<Player> result = repository.findById(p.getId());
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}

	void given_valid_playerId_when_updatePlayerPoints_then_return_1_playerPoints_are_updated() {
		// given
		Player p = new Player("name", "email", "");
		p = repository.save(p);
		// when
		int valueReturned = repository.updatePlayerPoints(p.getId(), 3);

		// then
		Optional<Player> result = repository.findById(p.getId());
		assertNotNull(result);
		assertEquals(1, valueReturned);
		assertTrue(result.isPresent());
		assertEquals(0, p.getPoints());
		assertEquals(3, result.get().getPoints());
	}

	void given_valid_playerId_and_valid_password_when_updatePassword_then_password_is_updated() {
		// given
		Player p = new Player("name", "email", "password");
		String newPassword = "newPassword";
		p = repository.save(p);
		// when
		int valueReturned = repository.updatePassword(p.getId(), p.getPassword(), newPassword);

		// then
		Optional<Player> result = repository.findById(p.getId());
		assertEquals(1, valueReturned);
		assertNotNull(result);
		assertTrue(result.isPresent());
		assertEquals(newPassword, result.get().getPassword());
	}
	
	
	void given_valid_playerId_and_invalid_password_when_updatePassword_then_return_0_and_password_is_not_updated() {
		// given
		Player p = new Player("name", "email", "password");
		String invalidPassword = "invalidPassword";
		String newPassword = "newPassword";
		p = repository.save(p);
		// when
		int valueReturned = repository.updatePassword(p.getId(), invalidPassword, newPassword);

		// then
		Optional<Player> result = repository.findById(p.getId());
		assertEquals(0, valueReturned);
		assertNotNull(result);
		assertTrue(result.isPresent());
		assertNotEquals(newPassword, result.get().getPassword());
		assertEquals(p.getPassword(),result.get().getPassword());
	}
	
	void given_valid_playerId_and_new_name_when_updatePersonalData_then_personal_data_is_updated() {
		// given
		Player p = new Player("name", "email", "password");
		String newName = "newName";
		p = repository.save(p);
		// when
		int valueReturned = repository.updatePersonalData(p.getId(), newName, p.getEmail());

		// then
		Optional<Player> result = repository.findById(p.getId());
		assertEquals(1, valueReturned);
		assertNotNull(result);
		assertTrue(result.isPresent());
		assertEquals(newName, result.get().getFullName());
	}
	
	void given_valid_playerId_and_new_email_when_updatePersonalData_then_personal_data_is_updated() {
		// given
		Player p = new Player("name", "email", "password");
		String newEmail = "newEmail";
		p = repository.save(p);
		// when
		int valueReturned = repository.updatePersonalData(p.getId(), newEmail, p.getEmail());

		// then
		Optional<Player> result = repository.findById(p.getId());
		assertEquals(1, valueReturned);
		assertNotNull(result);
		assertTrue(result.isPresent());
		assertEquals(newEmail, result.get().getEmail());
	}

}
