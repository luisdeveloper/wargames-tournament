package com.git.luisdeveloper.wargames_tournament.service;

import java.util.List;

import com.git.luisdeveloper.wargames_tournament.entity.Player;
import com.git.luisdeveloper.wargames_tournament.exception.PlayerNotFoundException;


public interface PlayerService {
	public List<Player> getPlayers();
	
	public Player getPlayer(Long id) throws PlayerNotFoundException;
	
	public void createPlayer(Player player);
	
	public void updatePlayer(Player player) throws PlayerNotFoundException;
	
	public void deletePlayer(Player player) throws PlayerNotFoundException;
	
	
}
