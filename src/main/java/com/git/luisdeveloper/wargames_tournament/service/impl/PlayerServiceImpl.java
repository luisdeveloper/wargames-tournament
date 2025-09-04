package com.git.luisdeveloper.wargames_tournament.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.git.luisdeveloper.wargames_tournament.entity.Player;
import com.git.luisdeveloper.wargames_tournament.exception.PlayerNotFoundException;
import com.git.luisdeveloper.wargames_tournament.repository.PlayerRepository;
import com.git.luisdeveloper.wargames_tournament.service.PlayerService;

@Service
public class PlayerServiceImpl implements PlayerService{

	@Autowired
	private PlayerRepository repository;
	
	@Override
	public List<Player> getPlayers() {
		return repository.findAll();
	}

	@Override
	public Player getPlayer(Long id) throws PlayerNotFoundException {
		Optional<Player> player = repository.findById(id); 
		if(player.isEmpty()) {
			throw new PlayerNotFoundException();
		}
		return player.get();
	}

	@Override
	public void createPlayer(Player player) {
		repository.save(player);
	}

	@Override
	public void updatePlayer(Player player) throws PlayerNotFoundException {
		if(repository.findById(player.getId()).isEmpty()) {
			throw new PlayerNotFoundException();
		}
		repository.save(player);
	}

	@Override
	public void deletePlayer(Player player) throws PlayerNotFoundException {
		if(repository.findById(player.getId()).isEmpty()) {
			throw new PlayerNotFoundException();
		}
		repository.delete(player);
	}

}
