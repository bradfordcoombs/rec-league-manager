package dev.bc.sas.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/login")
class LoginController {


	@GetMapping
	String login() {
		return "login";
	}
}
