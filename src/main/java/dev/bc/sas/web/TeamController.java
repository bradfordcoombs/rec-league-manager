package dev.bc.sas.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.bc.sas.domain.Team;
import dev.bc.sas.domain.TeamService;
import dev.bc.sas.security.PermissionUtil;
import dev.bc.sas.security.Role;
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
	String team(@AuthenticationPrincipal Authentication authentication, @PathVariable Long teamId,
			Model model) {
		var team = teamService.getTeam(teamId).get();
		var login = (UserDetails) authentication.getPrincipal();
		var role = PermissionUtil.getRole(login);
		model.addAttribute("team", team);
		model.addAttribute("canEdit", role == Role.DIRECTOR);
		return "team";
	}

	@PostMapping
	@PreAuthorize("hasAuthority('DIRECTOR')")
	String saveTeam(@Valid TeamRequestModel team, BindingResult bindingResult) {
		teamService.saveTeam(new Team(team.teamId(), team.name()));
		return "redirect:/teams";
	}
}
