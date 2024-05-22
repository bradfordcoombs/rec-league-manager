package dev.bc.sas.domain;

import java.util.List;
import java.util.Optional;

public interface TeamService {
	List<Team> getAllTeams();

	Optional<Team> getTeam(Long id);

	Team saveTeam(Team team);
}
