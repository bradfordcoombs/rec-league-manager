package dev.bc.sas.security;

import org.springframework.security.core.GrantedAuthority;

record UserRole(Role role) implements GrantedAuthority {

	UserRole(String role) {
		this(Role.valueOf(role));
	}

	@Override
	public String getAuthority() {
		return role.name();
	}
}
