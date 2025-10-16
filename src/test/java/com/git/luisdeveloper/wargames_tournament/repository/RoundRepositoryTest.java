package com.git.luisdeveloper.wargames_tournament.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.git.luisdeveloper.wargames_tournament.entity.Match;
import com.git.luisdeveloper.wargames_tournament.entity.Player;
import com.git.luisdeveloper.wargames_tournament.entity.Round;
import com.git.luisdeveloper.wargames_tournament.entity.Tournament;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
public class RoundRepositoryTest {

	@Autowired
	private RoundRepository repository;

	@Autowired
	private MatchRepository matchRepository;

	@Autowired
	private TournamentRepository tournamentRepository;
	
	@Autowired
	private EntityManager entityManager;

	@Test
	void given_new_round_when_save_then_round_persisted_in_db() {
		// given
		Round r = new Round(1, LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(1L));
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
		Round r = repository.save(new Round(1, LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(1L)));
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
		Round r = repository.save(new Round(1, LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(1L)));
		Round roundUpdated = new Round(r.getId(), 3, LocalDate.now().plusDays(1L), LocalTime.now().plusHours(2L),
				LocalTime.now().plusHours(5L));
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
		Round r = repository.save(new Round(1, LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(1L)));
		// when
		repository.delete(r);

		// then
		Optional<Round> result = repository.findById(r.getId());
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}

	@Test
	void when_saving_round_with_matches_then_matches_are_persisted() {
		Player p1 = new Player("p1", "p1@mail.com", "");
		Player p2 = new Player("p2", "p2@mail.com", "");
		Match match = new Match(p1, p2);
		Round round = new Round();
		round.addMatch(match);

		repository.save(round);
		Round result = repository.findById(round.getId()).get();

		assertEquals(1, result.getMatches().size());
	}

	@Test
	void when_delete_round_then_matches_deleted_too() {
		Round round = new Round();
		round.addMatch(new Match(new Player("p1", "p1@mail.com", ""), new Player("p2", "p2@mail.com", "")));
		repository.save(round);

		repository.deleteById(round.getId());

		assertTrue(matchRepository.findAll().isEmpty());
	}

	@Test
	void given_valid_roundId_and_new_roundDate_beginTime_endTime_when_updateDates_then_return_1_and_roundDate_and_times_are_updated() {
		// given
		Round round = repository.save(new Round());
		// when
		LocalDate newDate = LocalDate.now();
		LocalTime newBeginTime = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
		LocalTime newEndTime = newBeginTime.plusHours(1L).truncatedTo(ChronoUnit.MINUTES);;
		int roundsUpdated = repository.updateDates(round.getId(), newDate, newBeginTime, newEndTime);
		entityManager.clear();
		// then
		Optional<Round> result = repository.findById(round.getId());
		assertEquals(1, roundsUpdated);
		assertEquals(newDate, result.get().getRoundDate());
		assertEquals(newBeginTime, result.get().getBeginTime());
		assertEquals(newEndTime, result.get().getEndTime());
	}

	@Test
	void given_invalid_roundId_and_new_roundDate_beginTime_endTime_when_updateDates_then_return_0_and_nothing_is_updated() {
		// given
		Round round = repository.save(new Round());
		// when
		LocalDate newDate = LocalDate.now();
		LocalTime newBeginTime = LocalTime.now();
		LocalTime newEndTime = newBeginTime.plusHours(1L);
		Long invalidRoundId = round.getId() + 12L;
		int roundsUpdated = repository.updateDates(invalidRoundId, newDate, newBeginTime, newEndTime);
		entityManager.clear();
		// then
		Optional<Round> result = repository.findById(round.getId());
		assertEquals(0, roundsUpdated);
		assertTrue(result.isPresent());
		assertNotEquals(newDate, result.get().getRoundDate());
		assertNotEquals(newBeginTime, result.get().getBeginTime());
		assertNotEquals(newEndTime, result.get().getEndTime());
	}

	@Test
	void given_valid_tournamentId_and_tournament_with_round_with_no_matches_when_findFirstPendingRound_then_return_round() {
		// given
		Tournament t = new Tournament(null, "name",LocalDate.now(),LocalDate.now().plusDays(1L),"location",0,0);
		t.addRound(new Round());
		t = tournamentRepository.save(t);
		Long roundId = t.getRounds().get(0).getId();
		
		// when
		Optional<Round> result = repository.findFirstPendingRound(t.getId());
		entityManager.clear();
		// then
		assertTrue(result.isPresent());
		assertEquals(roundId, result.get().getId());
	}

	@Test
	void given_invalid_tournamentId_and_tournament_with_round_with_no_matches_when_findFirstPendingRound_then_return_empty_optional() {
		// given
		Tournament t = new Tournament(null, "name",LocalDate.now(),LocalDate.now().plusDays(1L),"location",0,0);
		t.addRound(new Round(LocalDate.now(),LocalTime.now(),LocalTime.now().plusHours(1L)));
		t = tournamentRepository.save(t);
		entityManager.clear();
		// when
		Long invalidTournamentId = t.getId() + 12L;
		Optional<Round> result = repository.findFirstPendingRound(invalidTournamentId);
		// then
		assertTrue(result.isEmpty());
	}

}
