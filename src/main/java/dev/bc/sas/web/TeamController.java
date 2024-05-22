package dev.bc.sas.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	String teamList(Model model) {
		var teams = teamService.getAllTeams();
		model.addAttribute("teams", teams);
		return "teams";
	}

	@GetMapping("/${teamId}")
	@PreAuthorize("hasPermission(#teamId, 'team', 'read')")
	String team(@PathVariable Long teamId, Model model) {
		var team = teamService.getTeam(teamId).get();
		model.addAttribute("team", team);
		return "team";
	}

	@PostMapping
	@PreAuthorize("hasAuthority('DIRECTOR')")
	String saveTeam(@Valid TeamRequestModel team, BindingResult bindingResult) {
		teamService.saveTeam(new Team(team.teamId(), team.name()));
		return "redirect:/teams";
	}
}
