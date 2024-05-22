package dev.bc.sas.domain;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.bc.sas.exception.EntityNotFoundException;

@Service
class RosterManager implements RosterService {
	private final TeamService teamService;
	private final PlayerService playerService;

	RosterManager(TeamService teamService, PlayerService playerService) {
		this.teamService = teamService;
		this.playerService = playerService;
	}

	@Override
	public void assignPlayersToTeam(Long teamId, List<Long> playerIds) {
		var team = getTeam(teamId);
		var players = playerService.getPlayers(playerIds).stream().map(player -> player.assignTo(team)).toList();
		playerService.savePlayers(players);
	}

	private Team getTeam(Long teamId) {
		return teamId == null ? null
				: teamService.getTeam(teamId).orElseThrow(() -> new EntityNotFoundException(teamId.toString()));
	}
}
