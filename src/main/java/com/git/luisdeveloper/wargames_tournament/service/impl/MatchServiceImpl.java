package com.git.luisdeveloper.wargames_tournament.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.git.luisdeveloper.wargames_tournament.dto.MatchDTO;
import com.git.luisdeveloper.wargames_tournament.dto.UpdateMatchDTO;
import com.git.luisdeveloper.wargames_tournament.enums.MatchResult;
import com.git.luisdeveloper.wargames_tournament.mappers.MatchMapper;
import com.git.luisdeveloper.wargames_tournament.repository.MatchRepository;
import com.git.luisdeveloper.wargames_tournament.service.MatchService;
import com.git.luisdeveloper.wargames_tournament.service.PlayerService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Validated
public class MatchServiceImpl implements MatchService, MatchInternalService {

	@Autowired
	private MatchRepository repository;

	@Autowired
	private PlayerService playerService;

	@Override
	@Transactional
	public void solveMatches(List<UpdateMatchDTO> matches) {
		matches.stream().forEach(x -> solveMatch(x));
	}

	@Override
	@Transactional
	public void solveMatch(@Valid UpdateMatchDTO dto) {
		solveMatch(dto.matchId(), dto.player1Id(), dto.player2Id(), dto.result());
	}

	@Override
	@Transactional
	public void solveMatch(Long matchId, Long player1Id, Long player2Id, MatchResult result) {
		repository.updateMatchResult(matchId, result);
		switch (result) {
		case PLAYER_1_VICTORY:
			playerService.updatePlayerPoints(player1Id, 3);
			playerService.updatePlayerPoints(player2Id, 0);
			break;
		case BYE:
			playerService.updatePlayerPoints(player1Id, 3);
			break;
		case PLAYER_2_VICTORY:
			playerService.updatePlayerPoints(player1Id, 0);
			playerService.updatePlayerPoints(player2Id, 3);
			break;
		case TIE:
			playerService.updatePlayerPoints(player1Id, 1);
			playerService.updatePlayerPoints(player2Id, 1);
			break;
		}
	}

	@Override
	public List<MatchDTO> getMatches(Long roundId) {
		return repository.findAllByRound_Id(roundId).stream().map(x -> MatchMapper.toDto(x)).toList();
	}

}
