package dev.bc.sas.web;

import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dev.bc.sas.domain.PlayerService;

@Controller
@RequestMapping("/players")
class PlayerController {

	private final PlayerService playerService;

	PlayerController(PlayerService playerService) {
		this.playerService = playerService;
	}

	@GetMapping
	ModelAndView allPlayers() {
		return new ModelAndView("players", Map.of("players", Collections.emptyList()));
	}
}
