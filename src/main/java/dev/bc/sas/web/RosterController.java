package dev.bc.sas.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.bc.sas.domain.RosterService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/rosters")
class RosterController {

	private final RosterService rosterService;

	RosterController(RosterService rosterService) {
		this.rosterService = rosterService;
	}

	@PostMapping
	String modifyRoster(@Valid @ModelAttribute("roster") RosterModel roster) {
		rosterService.assignPlayersToTeam(roster.teamId(), roster.playerIds());
		return "home";
	}
}
