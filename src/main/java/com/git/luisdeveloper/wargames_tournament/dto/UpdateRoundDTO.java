package com.git.luisdeveloper.wargames_tournament.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record UpdateRoundDTO(long roundId, LocalDate roundDate, LocalTime beginTime, LocalTime endTime) {

}
