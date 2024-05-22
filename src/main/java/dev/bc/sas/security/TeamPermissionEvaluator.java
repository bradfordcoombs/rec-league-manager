package dev.bc.sas.security;

import java.io.Serializable;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import dev.bc.sas.domain.TeamService;

@Component
class TeamPermissionEvaluator implements EntityPermissionEvaluator {

	private final TeamService teamService;

	TeamPermissionEvaluator(TeamService teamService) {
		this.teamService = teamService;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		var principal = (Login) authentication.getPrincipal();
		if (principal.isDirector())
			return true;
		if (PermissionUtil.isWritePermission(permission))
			return false;

		return false;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		var login = (Login) authentication.getPrincipal();
		if (login.isDirector())
			return true;
		if (PermissionUtil.isWritePermission(permission))
			return false;

		return switch (login.role()) {
		case DIRECTOR -> true;
		case COACH -> teamService.getTeamForCoach(login.id()).isPresent();
		case PLAYER -> throw new UnsupportedOperationException("Unimplemented case: " + login.role());
		default -> throw new IllegalArgumentException("Unexpected value: " + login.role());
		};
	}


	@Override
	public String entitySimpleClassName() {
		return "team";
	}

}
