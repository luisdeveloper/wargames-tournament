package com.git.luisdeveloper.wargames_tournament.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.git.luisdeveloper.wargames_tournament.dto.PlayerRegistrationDTO;
import com.git.luisdeveloper.wargames_tournament.exception.TournamentNotFoundException;
import com.git.luisdeveloper.wargames_tournament.repository.TournamentRepository;
import com.git.luisdeveloper.wargames_tournament.service.RoundService;

@ExtendWith(MockitoExtension.class)
public class TournamentServiceTest {
	@Mock
	private RoundService roundService;
	
	@Mock
	private TournamentRepository repository;
	
	@InjectMocks
	private TournamentServiceImpl service;
	
	
	@Test
	void given_tournamentId_not_stored_in_db_whenInvocking_getTournament_then_throws_TournamentNotFoundException(){
		//given
		when(repository.findById(anyLong())).thenReturn(Optional.empty());
		Long tournamentId = 2L;
		//when
		assertThrows(TournamentNotFoundException.class, () ->  service.getTournament(tournamentId));
		
	}
	
	@Test
	void given_PlayerRegistrationDTO_with_tournamentId_not_stored_in_db_whenInvocking_addPlayer_then_throws_TournamentNotFoundException(){
		//given
		when(repository.findById(anyLong())).thenReturn(Optional.empty());
		Long tournamentId = 2L;
		PlayerRegistrationDTO dto = new PlayerRegistrationDTO(tournamentId, "name", "name@email", "xxxx");
		//when
		assertThrows(TournamentNotFoundException.class, () ->  service.addPlayer(dto));
		
	}
	
}
