package com.git.luisdeveloper.wargames_tournament.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.git.luisdeveloper.wargames_tournament.entity.Player;


public interface PlayerService {
	public List<Player> getPlayers();
}
