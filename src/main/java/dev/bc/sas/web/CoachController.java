package dev.bc.sas.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.bc.sas.domain.CoachService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/coaches")
class CoachController {

	private final CoachService coachService;

	CoachController(CoachService coachService) {
		this.coachService = coachService;
	}

	@GetMapping
	@PreAuthorize("hasRole('MANAGER')")
	String getCoaches(Model model) {
		var coaches = coachService.getCoaches();
		model.addAttribute("coaches", coaches);
		return "coaches";
	}

	@PostMapping
	@PreAuthorize("hasRole('MANAGER')")
	String createCoach(@Valid @ModelAttribute("coach") CoachModel coachModel) {
		coachService.createCoach(coachModel);
		return "redirect:/coaches";
	}
}
