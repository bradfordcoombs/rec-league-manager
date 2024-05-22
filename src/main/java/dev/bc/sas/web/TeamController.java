package dev.bc.sas.web;

import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dev.bc.sas.domain.Team;
import dev.bc.sas.domain.TeamService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/teams")
class TeamController {

	private final TeamService teamService;

	TeamController(TeamService teamService) {
		this.teamService = teamService;
	}

	@GetMapping
	ModelAndView teamList() {
		var teams = teamService.getAllTeams();
		return new ModelAndView("teams", Map.of("teams", teams));
	}

	@GetMapping("/${teamId}")
	@PreAuthorize("hasPermission(#teamId, 'team', 'read')")
	ModelAndView team(@PathVariable Long teamId) {
		var team = teamService.getTeam(teamId).get();
		return new ModelAndView("team", Map.of("team", team));
	}

	@PostMapping
	@PreAuthorize("hasAuthority('DIRECTOR')")
	String createTeam(@Valid TeamRequestModel team, BindingResult bindingResult) {
		teamService.createTeam(team.name());
		return "redirect:/teams";
	}

	@PutMapping("/${teamId}")
	@PreAuthorize("hasAuthority('DIRECTOR')")
	ModelAndView updateTeam(@PathVariable Long teamId, @ModelAttribute TeamRequestModel team) {
		teamService.updateTeam(new Team(teamId, team.name()));
		return teamList();
	}
}
