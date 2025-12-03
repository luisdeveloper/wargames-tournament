package com.git.luisdeveloper.wargames_tournament.service.impl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.git.luisdeveloper.wargames_tournament.dto.UpdateMatchDTO;
import com.git.luisdeveloper.wargames_tournament.enums.MatchResult;
import com.git.luisdeveloper.wargames_tournament.logging.ServiceLogFormatter;
import com.git.luisdeveloper.wargames_tournament.mappers.MatchMapper;
import com.git.luisdeveloper.wargames_tournament.repository.MatchRepository;
import com.git.luisdeveloper.wargames_tournament.service.PlayerService;

@ExtendWith(MockitoExtension.class)
public class MatchServiceTest {
	@Mock
	private MatchRepository repository;

	@Mock
	private PlayerService playerService;

	@Mock
	private MatchMapper mapper;
	
	@Mock 
	private ServiceLogFormatter formatter;

	@InjectMocks
	private MatchServiceImpl service;
	

	@Test
	void given_dto_with_MatchResult_Player1_VICTORY_when_invoking_solveMatch_then_calls_updatePlayerPoints_giving_3_points_to_player1_and_0_points_to_player2() {
		// given
		Long player1Id = 1L;
		Long player2Id = 2L;
		UpdateMatchDTO dto = new UpdateMatchDTO(1L, player1Id, player2Id, MatchResult.PLAYER_1_VICTORY);
		// when
		service.solveMatch(dto);
		// then
		verify(playerService).updatePlayerPoints(player1Id, 3);
		verify(playerService).updatePlayerPoints(player2Id, 0);
	}

	@Test
	void given_dto_with_MatchResult_BYE_when_invoking_solveMatch_then_calls_updatePlayerPoints_giving_3_points_to_player1() {
		// given
		Long player1Id = 1L;
		Long player2Id = 2L;
		UpdateMatchDTO dto = new UpdateMatchDTO(1L, player1Id, player2Id, MatchResult.BYE);
		// when
		service.solveMatch(dto);
		// then
		verify(playerService,times(1)).updatePlayerPoints(player1Id, 3);
	}

	@Test
	void given_dto_with_MatchResult_Player2_VICTORY_when_invoking_solveMatch_then_calls_updatePlayerPoints_giving_0_points_to_player1_and_3_points_to_player2() {
		// given
		Long player1Id = 1L;
		Long player2Id = 2L;
		UpdateMatchDTO dto = new UpdateMatchDTO(1L, player1Id, player2Id, MatchResult.PLAYER_2_VICTORY);
		// when
		service.solveMatch(dto);
		// then
		verify(playerService).updatePlayerPoints(player1Id, 0);
		verify(playerService).updatePlayerPoints(player2Id, 3);
	}

	@Test
	void given_dto_with_MatchResult_TIE_when_invoking_solveMatch_then_calls_updatePlayerPoints_giving_0_points_to_player1_and_3_points_to_player2() {
		// given
		Long player1Id = 1L;
		Long player2Id = 2L;
		UpdateMatchDTO dto = new UpdateMatchDTO(1L, player1Id, player2Id, MatchResult.TIE);
		// when
		service.solveMatch(dto);
		// then
		verify(playerService).updatePlayerPoints(player1Id, 1);
		verify(playerService).updatePlayerPoints(player2Id, 1);
	}
	///////////////////////////////////////////////
	///
	//////////////////////////////////////////////
	
	@Test
	void given_MatchResult_Player1_VICTORY_when_invoking_solveMatch_then_calls_updatePlayerPoints_giving_3_points_to_player1_and_0_points_to_player2() {
		// given
		Long player1Id = 1L;
		Long player2Id = 2L;
		// when
		service.solveMatch(1L, player1Id, player2Id, MatchResult.PLAYER_1_VICTORY);
		// then
		verify(playerService).updatePlayerPoints(player1Id, 3);
		verify(playerService).updatePlayerPoints(player2Id, 0);
	}

	@Test
	void given_MatchResult_BYE_when_invoking_solveMatch_then_calls_updatePlayerPoints_giving_3_points_to_player1() {
		// given
		Long player1Id = 1L;
		Long player2Id = 2L;
		// when
		service.solveMatch(1L, player1Id, player2Id, MatchResult.BYE);
		// then
		verify(playerService,times(1)).updatePlayerPoints(player1Id, 3);
	}

	@Test
	void given_MatchResult_Player2_VICTORY_when_invoking_solveMatch_then_calls_updatePlayerPoints_giving_0_points_to_player1_and_3_points_to_player2() {
		// given
		Long player1Id = 1L;
		Long player2Id = 2L;
		// when
		service.solveMatch(1L, player1Id, player2Id, MatchResult.PLAYER_2_VICTORY);
		// then
		verify(playerService).updatePlayerPoints(player1Id, 0);
		verify(playerService).updatePlayerPoints(player2Id, 3);
	}

	@Test
	void given_MatchResult_TIE_when_invoking_solveMatch_then_calls_updatePlayerPoints_giving_0_points_to_player1_and_3_points_to_player2() {
		// given
		Long player1Id = 1L;
		Long player2Id = 2L;
		// when
		service.solveMatch(1L, player1Id, player2Id, MatchResult.TIE);
		// then
		verify(playerService).updatePlayerPoints(player1Id, 1);
		verify(playerService).updatePlayerPoints(player2Id, 1);
	}
}
