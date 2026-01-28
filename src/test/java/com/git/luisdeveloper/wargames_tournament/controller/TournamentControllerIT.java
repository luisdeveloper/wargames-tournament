package com.git.luisdeveloper.wargames_tournament.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.git.luisdeveloper.wargames_tournament.dto.RoundDTO;
import com.git.luisdeveloper.wargames_tournament.dto.TournamentRegistrationDTO;
import com.git.luisdeveloper.wargames_tournament.entity.Round;
import com.git.luisdeveloper.wargames_tournament.entity.Tournament;
import com.git.luisdeveloper.wargames_tournament.repository.RoundRepository;
import com.git.luisdeveloper.wargames_tournament.repository.TournamentRepository;;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = AFTER_CLASS)
public class TournamentControllerIT {

	private Tournament sampleTournament;
	private Round sampleRound;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TournamentRepository repository;

	@Autowired
	private RoundRepository roundRepository;

	@BeforeEach
	void init() {
		sampleTournament = new Tournament(null, "tournament", LocalDate.now(), LocalDate.now(), "location", 1000, 10);
		sampleRound = new Round(LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(1L));
		sampleTournament.addRound(sampleRound);
		sampleTournament = repository.save(sampleTournament);
	}

	@AfterEach
	void clean() {
		repository.deleteAll();
		roundRepository.deleteAll();
	}

	@Test
	void given_valid_tournamentId_when_calling_getTournamentSummary_then_return_status_ok_and_TournamentSummaryDTO()
			throws Exception {
		// given
		Long tournamentId = sampleTournament.getId();
		// when then
		mockMvc.perform(get("/tournaments/" + tournamentId + "/summary").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("name", is(sampleTournament.getName())));

	}
	
	@Test
	void given_invalid_tournamentId_when_calling_getTournamentSummary_then_return_status_not_found()
			throws Exception {
		// given
		Long invalidTournamentId = sampleTournament.getId() + 100L;
		// when then
		mockMvc.perform(get("/tournaments/" + invalidTournamentId + "/summary").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());

	}

	@Test
	void given_valid_tournamentId_when_calling_getRound_then_return_status_ok_and_RoundDTO() throws Exception {
		// given
		Long tournamentId = sampleTournament.getId();
		// when then
		mockMvc.perform(get("/tournaments/" + tournamentId + "/current-round").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("roundDate", is(sampleRound.getRoundDate().toString())))
				.andExpect(jsonPath("beginTime", is(sampleRound.getBeginTime().toString())))
				.andExpect(jsonPath("endTime", is(sampleRound.getEndTime().toString())));
	}
	
	@Test
	void given_invalid_tournamentId_when_calling_getRound_then_return_status_not_found() throws Exception {
		// given
		Long invalidTournamentId = sampleTournament.getId() + 100L;
		// when then
		mockMvc.perform(get("/tournaments/" + invalidTournamentId + "/current-round").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	void given_valid_TournamentRegistrationDTO_when_calling_insertTournament_then_return_status_ok_and_TournamentSummaryDTO()
			throws Exception {
		// given
		TournamentRegistrationDTO dto = new TournamentRegistrationDTO("new tournament", LocalDate.now(),
				LocalDate.now().plusDays(10L), "new location", 30, 3, new ArrayList<RoundDTO>());
		String json = objectMapper.writeValueAsString(dto);
		// when then
		mockMvc.perform(post("/tournaments").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("name", is(dto.name())));

	}

	@Test
	void given_valid_tournamentId_when_calling_generateMatches_then_return_status_ok_and_RoundDTO() throws Exception {
		// given
		Long tournamentId = sampleTournament.getId();
		// when then
		mockMvc.perform(
				post("/tournaments/" + tournamentId + "/current-round/matches").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("roundDate", is(sampleRound.getRoundDate().toString())))
				.andExpect(jsonPath("beginTime", is(sampleRound.getBeginTime().toString())))
				.andExpect(jsonPath("endTime", is(sampleRound.getEndTime().toString())));
	}
	
	@Test
	void given_invalid_tournamentId_when_calling_generateMatches_then_return_status_not_found() throws Exception {
		// given
		Long invalidTournamentId = sampleTournament.getId() + 100L;
		// when then
		mockMvc.perform(
				post("/tournaments/" + invalidTournamentId + "/current-round/matches").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	void given_valid_tournamentId_when_calling_deleteTournament_then_return_status_ok() throws Exception {
		// given
		Long tournamentId = sampleTournament.getId();
		// when then
		mockMvc.perform(delete("/tournaments/" + tournamentId).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());

	}
	
	@Test
	void given_invalid_tournamentId_when_calling_deleteTournament_then_return_status_ok() throws Exception {
		// given
		Long invalidTournamentId = sampleTournament.getId() + 100L;
		// when then
		mockMvc.perform(delete("/tournaments/" + invalidTournamentId).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());

	}

}
