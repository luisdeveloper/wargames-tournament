package com.git.luisdeveloper.wargames_tournament.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.git.luisdeveloper.wargames_tournament.dto.PlayerRankingDTO;
import com.git.luisdeveloper.wargames_tournament.dto.UpdatePasswordDTO;
import com.git.luisdeveloper.wargames_tournament.dto.UpdatePersonalDataDTO;
import com.git.luisdeveloper.wargames_tournament.exception.InvalidCredentialsException;
import com.git.luisdeveloper.wargames_tournament.exception.PlayerNotFoundException;
import com.git.luisdeveloper.wargames_tournament.mappers.PlayerMapper;
import com.git.luisdeveloper.wargames_tournament.repository.PlayerRepository;
import com.git.luisdeveloper.wargames_tournament.service.PlayerService;

import jakarta.transaction.Transactional;

@Service
public class PlayerServiceImpl implements PlayerService {

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
		if (!repository.existsById(dto.playerId())) {
			throw new PlayerNotFoundException();
		}
		int updatedEntries = repository.updatePassword(dto.playerId(), dto.oldPassword(), dto.newPassword());
		if (updatedEntries == 0)
			throw new InvalidCredentialsException();

	}

	@Override
	public List<PlayerRankingDTO> getRanking() {
		return repository.findAll().stream().map(x -> PlayerMapper.toPlayerRankingDTO(x)).sorted().toList();
	}

	@Override
	public int updatePlayerPoints(Long id, int points) {
		return repository.updatePlayerPoints(id, points);
	}

	@Override
	public List<PlayerRankingDTO> getPlayers() {
		return repository.findAll().stream().map(x -> PlayerMapper.toPlayerRankingDTO(x)).toList();
	}

	@Override
	public void deletePlayer(Long id) throws PlayerNotFoundException {
		if (repository.existsById(id))
			repository.deleteById(id);
		else
			throw new PlayerNotFoundException();
	}

}
