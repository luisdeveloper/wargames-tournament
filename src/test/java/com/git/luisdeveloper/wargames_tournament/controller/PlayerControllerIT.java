package com.git.luisdeveloper.wargames_tournament.controller;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.git.luisdeveloper.wargames_tournament.dto.PlayerRegistrationDTO;
import com.git.luisdeveloper.wargames_tournament.dto.UpdatePasswordDTO;
import com.git.luisdeveloper.wargames_tournament.dto.UpdatePersonalDataDTO;
import com.git.luisdeveloper.wargames_tournament.entity.Player;
import com.git.luisdeveloper.wargames_tournament.entity.Tournament;
import com.git.luisdeveloper.wargames_tournament.repository.PlayerRepository;
import com.git.luisdeveloper.wargames_tournament.repository.TournamentRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PlayerControllerIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private PlayerRepository repository;

	@Autowired
	private TournamentRepository tournamentRepository;

	private Player samplePlayer1;

	private Tournament sampleTournament;

	@BeforeEach
	void init() {

		sampleTournament = tournamentRepository.save(new Tournament(null, "fake tournament", LocalDate.now(),
				LocalDate.now().plusDays(1L), "fake location", 0, 0));
		samplePlayer1 = new Player("name 1", "name1@name.com", "1234");
		repository.save(samplePlayer1);
		repository.saveAll(List.of(new Player("name 2", "name2@name.com", "1234")));
		repository.flush();
	}

	@AfterEach
	void clean() {
		tournamentRepository.deleteAll();
		repository.deleteAll();
	}

	@Test
	void whenCalling_getPLayers_return_allPlayersFromDb() throws Exception {
		mockMvc.perform(get("/players/ranking").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()", is(2)))
				.andExpect(jsonPath("$[*].name", containsInAnyOrder("name 1", "name 2")));
	}

	@Test
	void given_valid_PlayerRegistrationDTO_whenCalling_insertPlayer_return_message_created() throws Exception {
		// given
		PlayerRegistrationDTO dto = new PlayerRegistrationDTO(sampleTournament.getId(), "new player",
				"new player@player", "password");
		String json = objectMapper.writeValueAsString(dto);
		// when then
		mockMvc.perform(post("/players").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().string("Player succesfully created"));
	}
	
	@Test
	void given_valid_UpdatePersonalDataDTO_whenCalling_updatePlayerPersonalData_then_return_message_update_ok()
			throws Exception {
		// given
		UpdatePersonalDataDTO dto = new UpdatePersonalDataDTO(samplePlayer1.getId(), samplePlayer1.getFullName() + "a",
				"new@email");
		String json = objectMapper.writeValueAsString(dto);
		// when then
		mockMvc.perform(patch("/players/personal-data").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().string("Player succesfully updated"));
	}
	
	@Test
	void given_valid_UpdatePersonalDataDTO_with_invalid_id_whenCalling_updatePlayerPersonalData_then_return_status_not_found()
			throws Exception {
		// given
		Long invalidId = samplePlayer1.getId() + 10L;
		UpdatePersonalDataDTO dto = new UpdatePersonalDataDTO(invalidId, samplePlayer1.getFullName() + "a",
				"new@email");
		String json = objectMapper.writeValueAsString(dto);
		// when then
		mockMvc.perform(patch("/players/personal-data").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	@Test
	void given_valid_UpdatePasswordDTO_when_calling_updatePlayerPassword_then_return_message_update_ok()
			throws Exception {
		// given
		UpdatePasswordDTO dto = new UpdatePasswordDTO(samplePlayer1.getId(), samplePlayer1.getPassword(), "xxxx");
		String json = objectMapper.writeValueAsString(dto);
		// when then
		mockMvc.perform(patch("/players/password").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().string("Player succesfully updated"));
	}
	
	@Test
	void given_valid_UpdatePasswordDTO_with_invalid_id_when_calling_updatePlayerPassword_then_return_status_not_found()
			throws Exception {
		// given
		Long invalidId = samplePlayer1.getId() + 10L;
		UpdatePasswordDTO dto = new UpdatePasswordDTO(invalidId, samplePlayer1.getPassword(), "xxxx");
		String json = objectMapper.writeValueAsString(dto);
		// when then
		mockMvc.perform(patch("/players/password").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}
	
	@Test
	void given_UpdatePasswordDTO_with_invalid_oldPassword_when_calling_updatePlayerPassword_then_return_status_unauthorized()
			throws Exception {
		// given
		String invalidOldPassword = samplePlayer1.getPassword() + "xx";
		UpdatePasswordDTO dto = new UpdatePasswordDTO(samplePlayer1.getId(), invalidOldPassword, "xxxx");
		String json = objectMapper.writeValueAsString(dto);
		// when then
		mockMvc.perform(patch("/players/password").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized());
	}

	@Test
	void given_valid_playerId_when_calling_deletePlayer_then_return_message_delete_ok() throws Exception {
		// given
		Long validPlayerId = samplePlayer1.getId();
		// when then
		mockMvc.perform(delete("/players/" + validPlayerId).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}
	
	@Test
	void given_invalid_playerId_when_calling_deletePlayer_then_return_status_not_found() throws Exception {
		// given
		Long invalidPlayerId = samplePlayer1.getId() + 10L;
		// when then
		mockMvc.perform(delete("/players/" + invalidPlayerId).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

}
