package com.git.luisdeveloper.wargames_tournament.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.git.luisdeveloper.wargames_tournament.entity.Player;
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

}
