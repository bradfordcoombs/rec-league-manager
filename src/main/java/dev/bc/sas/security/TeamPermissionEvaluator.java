package dev.bc.sas.security;

import java.io.Serializable;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import dev.bc.sas.domain.PlayerService;
import dev.bc.sas.domain.Team;

@Component
class TeamPermissionEvaluator implements EntityPermissionEvaluator {

	private final PlayerService playerService;

	TeamPermissionEvaluator(PlayerService playerService) {
		this.playerService = playerService;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		var team = (Team) targetDomainObject;
		return hasPermission(authentication, team.getId(), permission);
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		var teamId = targetId == null ? null : Long.valueOf(targetId.toString());
		return hasPermission(authentication, teamId, permission);

	}

	private boolean hasPermission(Authentication authentication, Long teamId, Object permission) {
		var login = (UserDetails) authentication.getPrincipal();
		var role = PermissionUtil.getRole(login);

		return switch (permission.toString()) {
		case "write" -> role == Role.DIRECTOR;
		case "read" -> role == Role.DIRECTOR || hasReadPermission(login, teamId);
		default -> throw new IllegalArgumentException("Unexpected value: " + permission.toString());
		};
	}

	private boolean hasReadPermission(UserDetails user, Long teamId) {
		if (teamId == null) {
			return false;
		}
		var playerOptional = playerService.getPlayerByEmail(user.getUsername());
		return playerOptional.filter(player -> (player.getTeam() == null && teamId == null)
				|| (player.getTeam() != null && player.getTeam().getId() == teamId)).isPresent();

	}

	@Override
	public String entitySimpleClassName() {
		return "team";
	}

}
