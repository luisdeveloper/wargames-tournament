package com.git.luisdeveloper.wargames_tournament.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record RoundDTO (Long roundId, int roundNumber, LocalDate roundDate,LocalTime beginTime, LocalTime endTime, List<MatchDTO> matches){
	
}
