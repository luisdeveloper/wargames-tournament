package com.git.luisdeveloper.wargames_tournament.controller;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.git.luisdeveloper.wargames_tournament.entity.Player;
import com.git.luisdeveloper.wargames_tournament.repository.PlayerRepository;
import org.springframework.http.MediaType;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PlayerControllerIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private PlayerRepository repository;

	@BeforeEach
	public void init() {
		repository.deleteAll();
		repository.saveAll(
				List.of(new Player("name 1", "name1@name.com", "1234"), new Player("name 2", "name2@name.com", "1234")));
		repository.flush();
	}
	
	@Test
	void whenCalling_getPLayers_return_allPlayersFromDb() throws Exception {
		mockMvc.perform(get("/players").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.length()",is(2)))
		.andExpect(jsonPath("$[*].fullName", containsInAnyOrder("name 1", "name 2")));
	}

}
