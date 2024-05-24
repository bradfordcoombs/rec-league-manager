package dev.bc.sas.domain;

import java.util.List;
import java.util.Optional;

import dev.bc.sas.web.PlayerRequestModel;

public interface PlayerService {

	Optional<Player> getPlayer(Long id);

	Optional<Player> getPlayerByEmail(String email);

	List<Player> getAllPlayers();

	List<Player> getPlayers(List<Long> playerIds);

	List<Player> getPlayersOnTeam(Long teamId);

	Player savePlayer(PlayerRequestModel playerModel);
}
