package com.git.luisdeveloper.wargames_tournament.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.git.luisdeveloper.wargames_tournament.entity.Round;
import com.git.luisdeveloper.wargames_tournament.entity.Tournament;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
public class TournamentRepositoryTest {
	@Autowired
	private RoundRepository roundRepository;

	@Autowired
	private TournamentRepository repository;

	@Test
	void given_new_tournament_when_save_then_tournament_persisted_in_db() {
		// given
		Tournament t = new Tournament("nombre", LocalDate.now(), LocalDate.now().plusDays(1L), "location", 12);
		// when
		Tournament result = repository.save(t);

		// then
		assertNotNull(result);
		assertEquals(t.getName(), result.getName());
		assertEquals(t.getBeginDate(), result.getBeginDate());
		assertEquals(t.getEndDate(), result.getEndDate());
		assertEquals(t.getLocation(), result.getLocation());
		assertEquals(t.getEntryPrice(), result.getEntryPrice());
	}

	@Test
	void given_tournament_stored_in_db_when_findById_then_returns_tournament() {
		// given
		Tournament t = repository
				.save(new Tournament("nombre", LocalDate.now(), LocalDate.now().plusDays(1L), "location", 12));
		// when
		Optional<Tournament> result = repository.findById(t.getId());

		// then
		assertNotNull(result);
		assertTrue(result.isPresent());
		assertNotNull(result);
		assertEquals(t.getName(), result.get().getName());
		assertEquals(t.getBeginDate(), result.get().getBeginDate());
		assertEquals(t.getEndDate(), result.get().getEndDate());
		assertEquals(t.getLocation(), result.get().getLocation());
		assertEquals(t.getEntryPrice(), result.get().getEntryPrice());
	}

	@Test
	void given_tournament_stored_in_db_when_update_then_returns_tournament_updated() {
		// given
		Tournament t = repository
				.save(new Tournament("nombre", LocalDate.now(), LocalDate.now().plusDays(1L), "location", 12));
		Tournament tournamentUpdated = new Tournament(t.getId(), "new name", LocalDate.now().plusDays(2L),
				LocalDate.now().plusDays(5L), "new location", 120, 11);
		// when
		Tournament result = repository.save(tournamentUpdated);

		// then
		assertNotNull(result);
		assertEquals(tournamentUpdated.getName(), result.getName());
		assertEquals(tournamentUpdated.getBeginDate(), result.getBeginDate());
		assertEquals(tournamentUpdated.getEndDate(), result.getEndDate());
		assertEquals(tournamentUpdated.getLocation(), result.getLocation());
		assertEquals(tournamentUpdated.getEntryPrice(), result.getEntryPrice());
		assertEquals(tournamentUpdated.getPrize(), result.getPrize());
	}

	@Test
	void given_tournament_stored_in_db_when_delete_then_tournament_is_no_longer_in_db() {
		// given
		Tournament t = repository
				.save(new Tournament("nombre", LocalDate.now(), LocalDate.now().plusDays(1L), "location", 12));
		// when
		repository.delete(t);

		// then
		Optional<Tournament> result = repository.findById(t.getId());
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}

	@Test
	void when_saving_tournament_with_rounds_rounds_are_saved_too() {
		// given
		Tournament t = new Tournament("nombre", LocalDate.now(), LocalDate.now().plusDays(1L), "location", 12);
		t.addRound(new Round());
		t = repository.save(t);
		Long roundId = t.getRounds().get(0).getId();
		// when
		Optional<Round> result = roundRepository.findById(roundId);
		// then
		assertNotNull(result);
		assertTrue(result.isPresent());
	}

	@Test
	void when_deleting_tournament_with_rounds_rounds_are_deleted_too() {
		// given
		Tournament t = new Tournament("nombre", LocalDate.now(), LocalDate.now().plusDays(1L), "location", 12);
		t.addRound(new Round());
		t = repository.save(t);
		Long roundId = t.getRounds().get(0).getId();
		// when
		repository.delete(t);
		// then
		Optional<Round> result = roundRepository.findById(roundId);
		assertNotNull(result);
		assertTrue(result.isEmpty());

	}

}
