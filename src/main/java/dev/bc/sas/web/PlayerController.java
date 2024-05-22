package dev.bc.sas.web;

import java.util.Collections;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.bc.sas.domain.PlayerService;
import dev.bc.sas.domain.TeamService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/players")
class PlayerController {

	private final PlayerService playerService;
	private final TeamService teamService;

	PlayerController(PlayerService playerService, TeamService teamService) {
		this.playerService = playerService;
		this.teamService = teamService;
	}

	@GetMapping
	String allPlayers(Model model) {
		model.addAttribute("players", Collections.emptyList());
		return "players";
	}

	@GetMapping("/{playerId}")
	@PreAuthorize("hasPermission(#playerId, 'player', 'read')")
	String getPlayer(@PathVariable Long playerId, Model model) {
		var player = playerService.getPlayer(playerId).get();
		var teams = teamService.getAllTeams();
		model.addAttribute("player", player);
		model.addAttribute("teams", teams);
		return "player";
	}


	@PostMapping
	@PreAuthorize("hasPermission(#playerId, 'player', 'write')")
	String savePlayer(@Valid PlayerRequestModel player) {
		playerService.savePlayer(player);
		return "redirect:/players";
	}
}
