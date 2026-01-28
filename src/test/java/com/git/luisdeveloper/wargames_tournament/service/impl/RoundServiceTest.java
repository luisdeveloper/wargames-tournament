package com.git.luisdeveloper.wargames_tournament.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.git.luisdeveloper.wargames_tournament.dto.RoundDTO;
import com.git.luisdeveloper.wargames_tournament.entity.Player;
import com.git.luisdeveloper.wargames_tournament.entity.Round;
import com.git.luisdeveloper.wargames_tournament.enums.MatchResult;
import com.git.luisdeveloper.wargames_tournament.exception.NoPendingRoundsException;
import com.git.luisdeveloper.wargames_tournament.exception.RoundAlreadyStartedException;
import com.git.luisdeveloper.wargames_tournament.logging.ServiceLogFormatter;
import com.git.luisdeveloper.wargames_tournament.repository.RoundRepository;

@ExtendWith(MockitoExtension.class)
public class RoundServiceTest {

	@Mock
	private RoundRepository repository;

	@Mock
	private MatchInternalService matchInternalService;
	
	@Mock
	private ServiceLogFormatter formatter;

	@InjectMocks
	private RoundServiceImpl service;
	
	@BeforeEach
	void init() {
		when(repository.findFirstPendingRound(anyLong())).thenReturn(Optional.of(new Round(1L,1,LocalDate.now(),LocalTime.now(),LocalTime.now().plusHours(1L),new ArrayList<>())));
	}


	@Test
	void given_round_with_no_matches_and_playersList_with_even_number_of_players_when_invocking_generateMatches_then_returns_RoundDTO_with_new_matches_and_number_of_matches_is_half_of_the_players()
			throws RoundAlreadyStartedException, NoPendingRoundsException {
		// given
		List<Player> players = new ArrayList<>();
		players.add(new Player());
		players.add(new Player());
		players.add(new Player());
		players.add(new Player());
		when(repository.save(any(Round.class))).then(AdditionalAnswers.returnsFirstArg());
		// when
		RoundDTO result = service.generateMatches(2L, players);
		// then
		assertNotNull(result);
		assertEquals(players.size() / 2 * 1.0, result.matches().size());
	}

	@Test
	void given_round_with_no_matches_and_playersList_with_odd_number_of_players_when_invocking_generateMatches_then_returns_RoundDTO_with_new_matches_and_number_of_matches_is_half_of_the_players_plus_1_and_last_match_is_a_bye()
			throws RoundAlreadyStartedException, NoPendingRoundsException {
		// given
		List<Player> players = new ArrayList<>();
		players.add(new Player());
		players.add(new Player());
		players.add(new Player());
		players.add(new Player());
		players.add(new Player());
		when(repository.save(any(Round.class))).then(AdditionalAnswers.returnsFirstArg());
		// when
		RoundDTO result = service.generateMatches(2L, players);
		// then
		assertNotNull(result);
		assertEquals(1 + players.size() / 2 * 1.0, result.matches().size());
		assertEquals(MatchResult.BYE, result.matches().getLast().result());
	}

	@Test
	void given_round_with_no_matches_and_players_have_points_when_invocking_generateMatches_then_returns_RoundDTO_with_new_matches_and_two_best_players_are_in_same_match()
			throws RoundAlreadyStartedException, NoPendingRoundsException {
		// given
		List<Player> players = new ArrayList<>();
		int bestScore = 13;
		players.add(new Player(1L, "Best player", "best-player@email", bestScore));
		players.add(new Player(4L, "4th player", "4player@email", 2));
		int secondBestScore = 12;
		players.add(new Player(2L, "2nd Best player", "2ndbest-player@email", secondBestScore));
		players.add(new Player(3L, "Third player", "player@email", 3));
		when(repository.save(any(Round.class))).then(AdditionalAnswers.returnsFirstArg());
		// when
		RoundDTO result = service.generateMatches(2L, players);
		// then
		assertNotNull(result);
		assertEquals(players.size() / 2 * 1.0, result.matches().size());
		assertEquals(bestScore, result.matches().getFirst().player1().points());
		assertEquals(secondBestScore, result.matches().getFirst().player2().points());
	}

	@Test
	void given_round_with_no_matches_and_players_have_points_when_invocking_generateMatches_then_returns_RoundDTO_with_new_matches_and_two_worst_players_are_in_same_match()
			throws RoundAlreadyStartedException, NoPendingRoundsException {
		// given
		List<Player> players = new ArrayList<>();
		int bestScore = 13;
		int secondBestScore = 12;
		int secondWorst = 3;
		int worstScore = 2;
		players.add(new Player(1L, "Best player", "best-player@email", bestScore));
		players.add(new Player(4L, "4th player", "4player@email", worstScore));
		players.add(new Player(2L, "2nd Best player", "2ndbest-player@email", secondBestScore));
		players.add(new Player(3L, "Third player", "player@email", secondWorst));
		when(repository.save(any(Round.class))).then(AdditionalAnswers.returnsFirstArg());
		// when
		RoundDTO result = service.generateMatches(2L, players);
		// then
		assertNotNull(result);
		assertEquals(players.size() / worstScore * 1.0, result.matches().size());
		assertEquals(secondWorst, result.matches().getLast().player1().points());
		assertEquals(worstScore, result.matches().getLast().player2().points());
	}

	@Test
	void given_round_with_no_matches_players_have_points_and_odd_number_of_players_when_invocking_generateMatches_then_returns_RoundDTO_with_new_matches_and_last_match_has_player_with_worstScore_and_MatchResult_is_BYE()
			throws RoundAlreadyStartedException, NoPendingRoundsException {
		// given
		List<Player> players = new ArrayList<>();
		int bestScore = 13;
		int secondBestScore = 12;
		int worstScore = 2;
		players.add(new Player(1L, "Best player", "best-player@email", bestScore));
		players.add(new Player(2L, "2nd Best player", "2ndbest-player@email", secondBestScore));
		players.add(new Player(3L, "Third player", "player@email", worstScore));
		when(repository.save(any(Round.class))).then(AdditionalAnswers.returnsFirstArg());

		// when
		RoundDTO result = service.generateMatches(2L, players);
		// then
		assertNotNull(result);
		assertEquals(1 + players.size() / worstScore * 1.0, result.matches().size());
		assertEquals(worstScore, result.matches().getLast().player1().points());
		assertEquals(MatchResult.BYE, result.matches().getLast().result());
	}

	@Test
	void given_tournamentId_with_no_pending_rounds_when_invocking_generateMatches_then_throws_NoPendingRoundsException() {
		// given
		List<Player> players = new ArrayList<>();
		long tournamentId = 2L;
		when(repository.findFirstPendingRound(anyLong())).thenReturn(Optional.empty());
		// when then
		assertThrows(NoPendingRoundsException.class, () -> {
			
			service.generateMatches(tournamentId, players);
		});
	}

}
