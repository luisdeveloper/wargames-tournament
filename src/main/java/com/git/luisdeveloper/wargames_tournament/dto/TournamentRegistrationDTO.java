package com.git.luisdeveloper.wargames_tournament.dto;

import java.time.LocalDate;
import java.util.List;

public record TournamentRegistrationDTO(String name, LocalDate beginDate, LocalDate endDate, String location, double prize, double entryPrice, List<RoundDTO>rounds) {

}
