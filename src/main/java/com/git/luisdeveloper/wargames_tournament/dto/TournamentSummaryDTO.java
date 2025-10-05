package com.git.luisdeveloper.wargames_tournament.dto;

import java.time.LocalDate;

public record TournamentSummaryDTO (Long id, String name, LocalDate beginDate, LocalDate endDate, double prize, double entryPrice, String location) {

}
