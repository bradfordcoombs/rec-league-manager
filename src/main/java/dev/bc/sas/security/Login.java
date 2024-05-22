package dev.bc.sas.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
record Login(
		@Id @GeneratedValue(strategy = GenerationType.AUTO) Long id,

		@Column(nullable = false, unique = true, updatable = false) String username,

		String password,

		@Enumerated(EnumType.STRING)
		Role role
) implements UserDetails {

	@Override
	public Collection<UserRole> getAuthorities() {
		return Collections.singletonList(new UserRole(role));
	}

	@Override
	public String getPassword() {
		return password();
	}

	@Override
	public String getUsername() {
		return username();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	boolean isDirector() {
		return this.role == Role.DIRECTOR;
	}
}
