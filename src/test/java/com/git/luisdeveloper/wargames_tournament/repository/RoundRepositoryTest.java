package com.git.luisdeveloper.wargames_tournament.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.git.luisdeveloper.wargames_tournament.entity.Round;

@DataJpaTest
@ActiveProfiles("test")
public class RoundRepositoryTest {
	
	@Autowired
	private RoundRepository repository;

	@Test
	void given_new_round_when_save_then_round_persisted_in_db() {
		// given
		Round r = new Round(1, LocalDate.now(), LocalTime.now(),LocalTime.now().plusHours(1L));
		// when
		Round result = repository.save(r);

		// then
		assertNotNull(result);
		assertEquals(r.getRoundNumber(), result.getRoundNumber());
		assertEquals(r.getRoundDate(), result.getRoundDate());
		assertEquals(r.getBeginTime(), result.getBeginTime());
		assertEquals(r.getEndTime(), result.getEndTime());
	}

	@Test
	void given_round_stored_in_db_when_findById_then_returns_round() {
		// given
		Round r = repository.save( new Round(1, LocalDate.now(), LocalTime.now(),LocalTime.now().plusHours(1L)));
		// when
		Optional<Round> result = repository.findById(r.getId());

		// then
		assertNotNull(result);
		assertTrue(result.isPresent());
		assertEquals(r.getRoundNumber(), result.get().getRoundNumber());
		assertEquals(r.getRoundDate(), result.get().getRoundDate());
		assertEquals(r.getBeginTime(), result.get().getBeginTime());
		assertEquals(r.getEndTime(), result.get().getEndTime());
	}

	@Test
	void given_round_stored_in_db_when_update_then_returns_round_updated() {
		// given
		Round r = repository.save( new Round(1, LocalDate.now(), LocalTime.now(),LocalTime.now().plusHours(1L)));
		Round roundUpdated = new Round(r.getId(), 3, LocalDate.now().plusDays(1L), LocalTime.now().plusHours(2L),LocalTime.now().plusHours(5L));
		// when
		Round result = repository.save(roundUpdated);

		// then
		assertNotNull(result);
		assertEquals(roundUpdated.getRoundNumber(), result.getRoundNumber());
		assertEquals(roundUpdated.getRoundDate(), result.getRoundDate());
		assertEquals(roundUpdated.getBeginTime(), result.getBeginTime());
		assertEquals(roundUpdated.getEndTime(), result.getEndTime());
	}

	@Test
	void given_round_stored_in_db_when_delete_then_round_is_no_longer_in_db() {
		// given
		Round r = repository.save( new Round(1, LocalDate.now(), LocalTime.now(),LocalTime.now().plusHours(1L)));
		// when
		repository.delete(r);

		// then
		Optional<Round> result = repository.findById(r.getId());
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}
}
