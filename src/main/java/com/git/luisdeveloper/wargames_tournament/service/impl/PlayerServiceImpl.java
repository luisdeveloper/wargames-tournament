package com.git.luisdeveloper.wargames_tournament.service.impl;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.git.luisdeveloper.wargames_tournament.dto.PlayerRankingDTO;
import com.git.luisdeveloper.wargames_tournament.dto.UpdatePasswordDTO;
import com.git.luisdeveloper.wargames_tournament.dto.UpdatePersonalDataDTO;
import com.git.luisdeveloper.wargames_tournament.exception.InvalidCredentialsException;
import com.git.luisdeveloper.wargames_tournament.exception.PlayerNotFoundException;
import com.git.luisdeveloper.wargames_tournament.logging.ServiceLogFormatter;
import com.git.luisdeveloper.wargames_tournament.mappers.PlayerMapper;
import com.git.luisdeveloper.wargames_tournament.repository.PlayerRepository;
import com.git.luisdeveloper.wargames_tournament.service.PlayerService;

import jakarta.transaction.Transactional;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private ServiceLogFormatter formatter;

	private static Logger logger = Logger.getLogger(PlayerServiceImpl.class.getName());

	@Autowired
	private PlayerRepository repository;

	@Override
	@Transactional
	public void updatePlayer(UpdatePersonalDataDTO player) throws PlayerNotFoundException {
		int updatedEntries = repository.updatePersonalData(player.playerId(), player.name(), player.email());
		if (updatedEntries == 0)
			throw new PlayerNotFoundException();

	}

	@Override
	@Transactional
	public void updatePlayer(UpdatePasswordDTO dto) throws InvalidCredentialsException, PlayerNotFoundException {
		logger.fine(formatter.request("updating player with id: " + dto.playerId()));
		if (!repository.existsById(dto.playerId())) {
			throw new PlayerNotFoundException();
		}
		int updatedEntries = repository.updatePassword(dto.playerId(), dto.oldPassword(), dto.newPassword());
		if (updatedEntries == 0)
			throw new InvalidCredentialsException();
		logger.fine(formatter.success("updating player with id: " + dto.playerId()));

	}

	@Override
	public List<PlayerRankingDTO> getRanking() {
		logger.fine(formatter.request("Get ranking"));
		var ranking = repository.findAll().stream().map(x -> PlayerMapper.toPlayerRankingDTO(x)).sorted().toList();
		logger.fine(formatter.success("Get ranking"));
		return ranking;
	}

	@Override
	public int updatePlayerPoints(Long id, int points) {
		logger.fine(formatter.request("Updating points of player with id: " + id));
		var numUpdatedPlayers = repository.updatePlayerPoints(id, points);
		logger.fine(formatter.success("Updating points of player with id: " + id));
		return numUpdatedPlayers;
	}

	@Override
	public List<PlayerRankingDTO> getPlayers() {
		logger.fine(formatter.request("Getting ranking"));
		var ranking = repository.findAll().stream().map(x -> PlayerMapper.toPlayerRankingDTO(x)).toList();
		logger.fine(formatter.success("Getting ranking"));
		return ranking;
	}

	@Override
	public void deletePlayer(Long id) throws PlayerNotFoundException {
		logger.fine(formatter.request("Deleting player with id: " + id));
		if (repository.existsById(id)) {
			repository.deleteById(id);
			logger.fine(formatter.success("Deleting player with id: " + id));
		}else {
			throw new PlayerNotFoundException();
		}
		
	}

}
