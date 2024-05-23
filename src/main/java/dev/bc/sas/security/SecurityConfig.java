package dev.bc.sas.security;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
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
//				.formLogin(Customizer.withDefaults())
				.formLogin(form -> form.defaultSuccessUrl("/teams"))
				.userDetailsService(userDetailsService)
				.build();
	}

	@Bean
	UserDetailsManager users(
			DataSource dataSource, 
			@Value("${bc.security.director.password:director123}") String directorPassword,
			@Value("${bc.security.player.password:player123}") String playerPassword, PasswordEncoder passwordEncoder
			) {
				var director = User.builder().password(passwordEncoder.encode(directorPassword))
						.roles(Role.DIRECTOR.name())
						.username("director@test.com").build();
				var player = User.builder().password(passwordEncoder.encode(playerPassword)).roles(Role.PLAYER.name())
						.username("player@test.com").build();
		var users = new JdbcUserDetailsManager(dataSource);
		users.createUser(director);
		users.createUser(player);
		return users;

	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
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
