package com.git.luisdeveloper.wargames_tournament.dto;

public record UpdatePasswordDTO(Long playerId, String oldPassword, String newPassword) {

}
