package dev.bc.sas.domain;

import java.util.List;
import java.util.Optional;

public interface TeamService {
	List<Team> getAllTeams();

	Optional<Team> getTeam(Long id);

	Optional<Team> getTeamForCoach(Long coachId);

	Team createTeam(String name);

	Team updateTeam(Team team);
}
