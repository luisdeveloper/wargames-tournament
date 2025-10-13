package com.git.luisdeveloper.wargames_tournament.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.git.luisdeveloper.wargames_tournament.dto.UpdateMatchDTO;
import com.git.luisdeveloper.wargames_tournament.entity.Match;
import com.git.luisdeveloper.wargames_tournament.entity.Player;
import com.git.luisdeveloper.wargames_tournament.enums.MatchResult;
import com.git.luisdeveloper.wargames_tournament.repository.MatchRepository;
import com.git.luisdeveloper.wargames_tournament.repository.PlayerRepository;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class MatchControllerIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MatchRepository repository;

	@Autowired
	private PlayerRepository playerRepository;

	private Player samplePlayer1;
	private Player samplePlayer2;
	private Player samplePlayer3;
	private Player samplePlayer4;

	private Match sampleMatch1;
	private Match sampleMatch2;

	@BeforeEach
	void init() {
		samplePlayer1 = new Player("player1", "player1@email", "");
		samplePlayer1 = playerRepository.save(samplePlayer1);
		samplePlayer2 = new Player("player2", "player2@email", "");
		samplePlayer2 = playerRepository.save(samplePlayer2);
		samplePlayer3 = new Player("player3", "player3@email", "");
		samplePlayer3 = playerRepository.save(samplePlayer3);
		samplePlayer4 = new Player("player4", "player4@email", "");
		samplePlayer4 = playerRepository.save(samplePlayer4);
		sampleMatch1 = new Match(samplePlayer1, samplePlayer2);
		sampleMatch2 = new Match(samplePlayer3, samplePlayer4);
		repository.saveAll(List.of(sampleMatch1, sampleMatch2));
	}

	@AfterEach
	void clean() {
		repository.deleteAll();
		playerRepository.deleteAll();
	}

	@Test
	void given_list_of_UpdateMatchDTO_when_calling_solveMatches_then_return_player_rankings() throws Exception {
		// given
		List<UpdateMatchDTO> list = List.of(
				new UpdateMatchDTO(sampleMatch1.getId(), samplePlayer1.getId(), samplePlayer2.getId(),
						MatchResult.PLAYER_1_VICTORY),
				new UpdateMatchDTO(sampleMatch2.getId(), samplePlayer3.getId(), samplePlayer4.getId(),
						MatchResult.TIE));
		String json = objectMapper.writeValueAsString(list);
		// when then
		mockMvc.perform(put("/matches/results").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$[*].email").exists())
			    .andExpect(jsonPath("$[*].points").exists()).andExpect(jsonPath("$.length()", is(4)));
	}
}
