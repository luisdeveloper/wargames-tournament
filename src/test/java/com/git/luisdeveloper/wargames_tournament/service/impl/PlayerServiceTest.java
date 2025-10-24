package com.git.luisdeveloper.wargames_tournament.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.git.luisdeveloper.wargames_tournament.dto.UpdatePasswordDTO;
import com.git.luisdeveloper.wargames_tournament.dto.UpdatePersonalDataDTO;
import com.git.luisdeveloper.wargames_tournament.exception.PlayerNotFoundException;
import com.git.luisdeveloper.wargames_tournament.mappers.PlayerMapper;
import com.git.luisdeveloper.wargames_tournament.repository.PlayerRepository;
import com.git.luisdeveloper.wargames_tournament.service.TournamentService;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {
	
	
	@Mock
	private PlayerRepository repository;

	@Mock
	private TournamentService tournamentService;

	@Mock
	private PlayerMapper mapper;
	
	@InjectMocks
	private PlayerServiceImpl service;
	
	@Test
	void given_non_existent_id_in_UpdatePersonalDataDTO_when_invoking_updatePlayer_then_throws_PlayerNotFoundException() throws PlayerNotFoundException {
		//given
		Long nonExistentId = -1L;
		UpdatePersonalDataDTO personalData = new UpdatePersonalDataDTO(nonExistentId, "name", "email");
		when(repository.updatePersonalData(eq(nonExistentId), anyString(), anyString())).thenReturn(0);
		
		//when //then
		assertThrows(PlayerNotFoundException.class, ()-> service.updatePlayer(personalData));
		
	}
	
	@Test
	void given_non_existent_id_in_UpdateCredentialsDTO_when_invoking_updatePlayer_then_throws_PlayerNotFoundException() throws PlayerNotFoundException {
		//given
		Long nonExistentId = -1L;
		UpdatePasswordDTO personalData = new UpdatePasswordDTO(nonExistentId, "name", "email");
		
		//when //then
		assertThrows(PlayerNotFoundException.class, ()-> service.updatePlayer(personalData));
		
	}
	
}
