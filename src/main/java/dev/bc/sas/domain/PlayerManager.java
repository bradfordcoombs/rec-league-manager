package dev.bc.sas.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.bc.sas.exception.EntityNotFoundException;
import dev.bc.sas.web.PlayerRequestModel;

@Service
class PlayerManager implements PlayerService {

	private final PlayerRepository playerRepository;

	PlayerManager(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
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

	public Player createPlayer(PlayerRequestModel playerModel) {
		return playerRepository.save(Player.init(playerModel));
	}

	public Player updatePlayer(Long id, PlayerRequestModel playerModel) {
		var player = playerRepository.findById(id).map(playerEntity -> playerEntity.update(playerModel))
				.orElseThrow(() -> new EntityNotFoundException(id.toString()));
		return playerRepository.save(player);
	}

	public void savePlayers(List<Player> players) {
		playerRepository.saveAll(players);
	}
}
