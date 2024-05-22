package dev.bc.sas.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
class UserController {

	@GetMapping
	ModelAndView userList() {
		return new ModelAndView("");
	}
}
