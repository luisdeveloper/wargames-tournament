package com.git.luisdeveloper.wargames_tournament.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalTime;
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
import com.git.luisdeveloper.wargames_tournament.dto.UpdateRoundDTO;
import com.git.luisdeveloper.wargames_tournament.entity.Match;
import com.git.luisdeveloper.wargames_tournament.entity.Player;
import com.git.luisdeveloper.wargames_tournament.entity.Round;
import com.git.luisdeveloper.wargames_tournament.repository.PlayerRepository;
import com.git.luisdeveloper.wargames_tournament.repository.RoundRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class RoundControllerIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private RoundRepository repository;

	@Autowired
	private PlayerRepository playerRepository;

	private Round sampleRound;

	@BeforeEach
	void init() {
		sampleRound = new Round();
		Player player1 = new Player("player1", "player1@email", "");
		Player player2 = new Player("player2", "player2@email", "");
		Player player3 = new Player("player3", "player3@email", "");
		Player player4 = new Player("player4", "player4@email", "");
		playerRepository.saveAll(List.of(player1, player2, player3, player4));
		sampleRound.addMatch(List.of(new Match(player1, player2), new Match(player3, player4)));
		sampleRound = repository.save(sampleRound);
		repository.saveAll(List.of(new Round()));
	}
	
	@AfterEach
	void finish() {
		repository.deleteAll();
		playerRepository.deleteAll();
	}

	@Test
	void given_valid_roundId_with_matches_when_calling_getMatches_then_return_matches_from_db() throws Exception {

		mockMvc.perform(get("/rounds/" + sampleRound.getId() + "/matches").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()", is(2))).andExpect(jsonPath("$[*].matchId").isNotEmpty())
				.andExpect(jsonPath("$[0].player1").isNotEmpty()).andExpect(jsonPath("$[0].player2").isNotEmpty());
	}

	@Test
	void given_valid_UpdateRoundDTO_when_calling_updateRoundDates_then_return_response_no_content() throws Exception {
		// given
		UpdateRoundDTO dto = new UpdateRoundDTO(sampleRound.getId(), LocalDate.now().plusDays(2L),
				LocalTime.now().plusHours(3L), LocalTime.now().plusHours(6L));
		String json = objectMapper.writeValueAsString(dto);
		// when then
		mockMvc.perform(patch("/rounds/" + dto.roundId()).contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
	}

	@Test
	void given_valid_roundId_when_calling_delete_then_return_response_no_content() throws Exception {
		// given
		Long validRoundId = sampleRound.getId();
		// when then
		mockMvc.perform(delete("/rounds/" + validRoundId).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

}
