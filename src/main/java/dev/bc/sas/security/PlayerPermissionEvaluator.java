package dev.bc.sas.security;

import java.io.Serializable;

import org.springframework.security.core.Authentication;

class PlayerPermissionEvaluator implements EntityPermissionEvaluator {

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		var principal = (Login) authentication.getPrincipal();
		if (principal.isDirector())
			return true;
		return false;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		var principal = (Login) authentication.getPrincipal();
		if (principal.isDirector())
			return true;

		return false;
	}

	@Override
	public String entitySimpleClassName() {
		return "player";
	}

}
