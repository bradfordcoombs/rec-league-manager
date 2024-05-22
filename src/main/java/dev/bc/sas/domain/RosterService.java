package dev.bc.sas.domain;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.bc.sas.exception.EntityNotFoundException;

@Service
public interface RosterService {
	/**
	 * Assign or remove a set of players to or from a team. If the team ID is null,
	 * remove them from their current team
	 * 
	 * @param teamId    ID of the team the players should be assigned to. Null to
	 *                  remove
	 * @param playerIds ID's of the players to be assigned or removed
	 * 
	 * @throws EntityNotFoundException if a team ID is provided but the team does
	 *                                 not exist
	 */
	void assignPlayersToTeam(Long teamId, List<Long> playerIds);
}
