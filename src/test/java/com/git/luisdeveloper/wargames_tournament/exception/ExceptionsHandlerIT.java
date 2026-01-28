package com.git.luisdeveloper.wargames_tournament.exception;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.git.luisdeveloper.wargames_tournament.service.PlayerService;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class ExceptionsHandlerIT {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PlayerService service;
	
	@Test
    void whenServiceThrowsRuntimeException_thenReturn500() throws Exception {
        when(service.getPlayers()).thenThrow(new RuntimeException());

        mockMvc.perform(get("/players/ranking")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isInternalServerError());
    }
	
}
