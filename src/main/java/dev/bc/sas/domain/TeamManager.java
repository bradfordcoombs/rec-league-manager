package dev.bc.sas.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
class TeamManager implements TeamService {

	private final TeamRepository teamRepository;

	TeamManager(TeamRepository teamRepository) {
		this.teamRepository = teamRepository;
	}

	@Override
	public List<Team> getAllTeams() {
		return teamRepository.findAll();
	}

	@Override
	public Optional<Team> getTeam(Long id) {
		return teamRepository.findById(id);
	}

	@Override
	public Team saveTeam(Team team) {
		return teamRepository.save(team);
	}

}
