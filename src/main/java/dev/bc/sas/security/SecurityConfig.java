package dev.bc.sas.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.ObjectUtils;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http, UserDetailsService userDetailsService) throws Exception {
		return http
				.authorizeHttpRequests(
						authorize -> authorize.requestMatchers("/console/**").permitAll().anyRequest().authenticated())
				.csrf(csfr -> csfr.ignoringRequestMatchers("/console/**"))
				.formLogin(form -> form.loginPage("/login").permitAll())
				.userDetailsService(userDetailsService)
				.build();
	}

	@Bean
	MethodSecurityExpressionHandler methodSecurityExpressionHandler(List<EntityPermissionEvaluator> evaluators) {
		var expressionHandler = new DefaultMethodSecurityExpressionHandler();
		if (!ObjectUtils.isEmpty(evaluators)) {
			expressionHandler.setPermissionEvaluator(new CustomPermissionEvaluator(evaluators));
		}
		return expressionHandler;
	}

}
