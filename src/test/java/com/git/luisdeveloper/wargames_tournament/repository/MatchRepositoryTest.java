package com.git.luisdeveloper.wargames_tournament.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.git.luisdeveloper.wargames_tournament.entity.Match;
import com.git.luisdeveloper.wargames_tournament.entity.Player;
import com.git.luisdeveloper.wargames_tournament.enums.MatchResult;

import jakarta.persistence.EntityManager;


@DataJpaTest
@ActiveProfiles("test")
public class MatchRepositoryTest {
	@Autowired
	private MatchRepository repository;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	private Player player;
	private Player player2;
	private Player player3;
	private Player player4;
	
	@BeforeEach
	void init() {
		player = new Player("aa", "aa", "");
		player2 = new Player("ss","ss","");
		player3 = new Player("dd","dd","");
		player4 = new Player("ff","ff","");
		playerRepository.saveAll(List.of(player,player2,player3,player4));
	}
	

	@Test
	void given_new_match_when_save_then_match_persisted_in_db() {
		// given
		Match m = new Match(new Player("", "", ""), new Player("", "", ""));
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
		Match m = new Match(new Player("", "", ""), new Player("", "", ""));
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
		Match m = new Match(new Player("", "", ""), new Player("", "", ""));
		m = repository.save(m);
		Match matchUpdated = new Match(m.getId(), m.getPlayer1(), m.getPlayer2(), MatchResult.PLAYER_1_VICTORY);
		// when
		Match result = repository.save(matchUpdated);

		// then
		assertNotNull(result);
		assertEquals(matchUpdated.getResult(), result.getResult());
	}

	@Test
	void given_match_stored_in_db_when_delete_then_returns_match_is_no_longer_in_db() {
		// given
		Match m = new Match(new Player("", "", ""), new Player("", "", ""));
		m = repository.save(m);
		// when
		repository.delete(m);

		// then
		Optional<Match> result = repository.findById(m.getId());
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}

	@Test
	void given_valid_matchId_and_matchResult_when_updateMatchResult_then_return_1_and_match_is_updated() {
		// given
		Match m = repository.save(new Match(player,player));
		

		MatchResult newMatchResult = MatchResult.PLAYER_1_VICTORY;
		// when
		int result = repository.updateMatchResult(m.getId(), newMatchResult);
		entityManager.clear();

		// then
		Optional<Match> matchResult = repository.findById(m.getId());
		assertEquals(1, result);
		assertNotNull(matchResult);
		assertTrue(matchResult.isPresent());
		assertEquals(newMatchResult, matchResult.get().getResult());
	}
	
	@Test
	void given_list_of_2_valid_matchIds_and_matchResult_when_updateMatchResults_then_return_2_and_matches_are_updated() {
		// given
		Match m = repository.save( new Match(player, player2));
		Match m2 = repository.save( new Match(player3,player4));
		MatchResult newMatchResult = MatchResult.PLAYER_1_VICTORY;
		// when
		int result = repository.updateMatchResults(List.of( m.getId(), m2.getId()), newMatchResult);
		entityManager.clear();
		// then
		Optional<Match> matchResult = repository.findById(m.getId());
		Optional<Match> matchResult2 = repository.findById(m2.getId());
		assertEquals(2, result);
		assertNotNull(matchResult);
		assertNotNull(matchResult2);
		assertTrue(matchResult.isPresent());
		assertTrue(matchResult2.isPresent());
		assertEquals(newMatchResult, matchResult.get().getResult());
		assertEquals(newMatchResult, matchResult2.get().getResult());
	}
}
