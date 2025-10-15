package com.git.luisdeveloper.wargames_tournament.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.git.luisdeveloper.wargames_tournament.entity.Match;
import com.git.luisdeveloper.wargames_tournament.entity.Player;
import com.git.luisdeveloper.wargames_tournament.enums.MatchResult;

@DataJpaTest
@ActiveProfiles("test")
public class MatchRepositoryTest {
	@Autowired
	private MatchRepository repository;

	@Test
	void given_new_match_when_save_then_match_persisted_in_db() {
		// given
		Match m = new Match(new Player("","",""), new Player("","",""));
		// when
		Match result = repository.save(m);

		// then
		assertNotNull(result);
		assertEquals(m.getPlayer1(), result.getPlayer1());
		assertEquals(m.getPlayer2(), result.getPlayer2());
	}

	@Test
	void given_match_stored_in_db_when_findById_then_returns_match() {
		// given
		Match m = new Match(new Player("","",""), new Player("","",""));
		m = repository.save(m);
		// when
		Optional<Match> result = repository.findById(m.getId());

		// then
		assertNotNull(result);
		assertTrue(result.isPresent());
		assertEquals(m.getPlayer1(), result.get().getPlayer1());
		assertEquals(m.getPlayer2(), result.get().getPlayer2());
	}

	@Test
	void given_match_stored_in_db_when_update_then_returns_match_updated() {
		// given
		Match m = new Match(new Player("","",""), new Player("","",""));
		m = repository.save(m);
		Match matchUpdated = new Match(m.getId(), m.getPlayer1(),m.getPlayer2(), MatchResult.PLAYER_1_VICTORY);
		// when
		Match result = repository.save(matchUpdated);

		// then
		assertNotNull(result);
		assertEquals(matchUpdated.getResult(), result.getResult());
	}

	@Test
	void given_match_stored_in_db_when_delete_then_returns_match_updated() {
		// given
		Match m = new Match(new Player("","",""), new Player("","",""));
		m = repository.save(m);
		// when
		repository.delete(m);

		// then
		Optional<Match> result = repository.findById(m.getId());
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}
}
