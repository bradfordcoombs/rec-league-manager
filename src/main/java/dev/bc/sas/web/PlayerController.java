package dev.bc.sas.web;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.bc.sas.domain.PlayerService;
import dev.bc.sas.domain.TeamService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/players")
class PlayerController {

	private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);

	private final PlayerService playerService;
	private final TeamService teamService;

	PlayerController(PlayerService playerService, TeamService teamService) {
		this.playerService = playerService;
		this.teamService = teamService;
	}

	@GetMapping
	String allPlayers(Model model) {
		logger.info("Getting all players");
		model.addAttribute("players", Collections.emptyList());
		return "players";
	}

	@GetMapping("/{playerId}")
	@PreAuthorize("hasPermission(#playerId, 'player', 'read')")
	String getPlayer(@PathVariable("playerId") Long playerId, Model model) {
		var player = playerService.getPlayer(playerId).get();
		var teams = teamService.getAllTeams();
		model.addAttribute("player", player);
		model.addAttribute("teams", teams);
		return "player";
	}


	@PostMapping
	@PreAuthorize("hasPermission(#player, 'write')")
	String savePlayer(@Valid @ModelAttribute("player") PlayerRequestModel player) {
		playerService.savePlayer(player);
		return "redirect:/players";
	}
}
