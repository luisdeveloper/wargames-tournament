package com.git.luisdeveloper.wargames_tournament.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public record RoundDTO (Long roundId, int roundNumber, LocalDate roundDate,@JsonFormat(pattern = "HH:mm")LocalTime beginTime, @JsonFormat(pattern = "HH:mm")LocalTime endTime, List<MatchDTO> matches){
	
}
