package dev.bc.sas.domain;

import java.util.List;
import java.util.Optional;

import dev.bc.sas.web.PlayerRequestModel;

public interface PlayerService {

	Optional<Player> getPlayer(Long id);

	List<Player> getPlayers(List<Long> playerIds);

	List<Player> getPlayersOnTeam(Long teamId);

	Player createPlayer(PlayerRequestModel playerModel);

	Player updatePlayer(Long id, PlayerRequestModel playerModel);

	void savePlayers(List<Player> players);
}
