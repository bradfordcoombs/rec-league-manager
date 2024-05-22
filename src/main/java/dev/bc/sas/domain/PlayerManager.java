package dev.bc.sas.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.bc.sas.web.PlayerRequestModel;

@Service
class PlayerManager implements PlayerService {

	private final PlayerRepository playerRepository;
	private final TeamService teamService;

	PlayerManager(PlayerRepository playerRepository, TeamService teamService) {
		this.playerRepository = playerRepository;
		this.teamService = teamService;
	}

	public Optional<Player> getPlayer(Long id) {
		return playerRepository.findById(id);
	}

	public List<Player> getPlayers(List<Long> playerIds) {
		return playerRepository.findAllById(playerIds);
	}

	public List<Player> getPlayersOnTeam(Long teamId) {
		return playerRepository.findByTeam_id(teamId);
	}

	public Player savePlayer(PlayerRequestModel playerModel) {
		var team = teamService.getTeam(playerModel.teamId()).get();
		var player = new Player(playerModel.id(), playerModel.firstName(), playerModel.lastName(), playerModel.email(),
				team);
		return playerRepository.save(player);
	}

	@Override
	public Optional<Player> getPlayerByEmail(String email) {
		return playerRepository.findOneByEmail(email);
	}

}
