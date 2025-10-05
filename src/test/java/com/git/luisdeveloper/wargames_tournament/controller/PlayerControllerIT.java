package com.git.luisdeveloper.wargames_tournament.controller;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

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
import com.git.luisdeveloper.wargames_tournament.entity.Player;
import com.git.luisdeveloper.wargames_tournament.entity.Tournament;
import com.git.luisdeveloper.wargames_tournament.mappers.PlayerMapper;
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

	@BeforeEach
	public void init() {
		repository.deleteAll();
		repository.saveAll(List.of(new Player("name 1", "name1@name.com", "1234"),
				new Player("name 2", "name2@name.com", "1234")));
		repository.flush();
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
		//given
		Tournament tournament = tournamentRepository.save(new Tournament(null, "fake tournament", LocalDate.now(), LocalDate.now().plusDays(1L), "fake location", 0, 0));
		PlayerRegistrationDTO dto = new PlayerRegistrationDTO(tournament.getId(), "new player", "new player@player", "password");
		String json = objectMapper.writeValueAsString(dto);
		Player entity = PlayerMapper.toEntity(dto);
		//when then
		mockMvc.perform(
				post("/players").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().string("Player succesfully created"));
	}

}
