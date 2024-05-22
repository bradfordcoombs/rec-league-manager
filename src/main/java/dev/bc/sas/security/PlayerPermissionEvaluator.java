package dev.bc.sas.security;

import java.io.Serializable;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import dev.bc.sas.domain.PlayerService;
import dev.bc.sas.web.PlayerRequestModel;

@Component
class PlayerPermissionEvaluator implements EntityPermissionEvaluator {

	private final PlayerService playerService;

	PlayerPermissionEvaluator(PlayerService playerService) {
		this.playerService = playerService;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		var player = (PlayerRequestModel) targetDomainObject;
		return hasPermission(authentication, player.id());
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		return hasPermission(authentication, targetId);
	}

	private boolean hasPermission(Authentication authentication, Serializable targetId) {
		var login = (UserDetails) authentication.getPrincipal();
		var role = PermissionUtil.getRole(login);
		var playerId = targetId == null ? null : Long.valueOf(targetId.toString());

		return switch (role) {
		case DIRECTOR -> true;
		case PLAYER -> playerId != null && playerService.getPlayer(playerId)
				.filter(player -> player.getEmail().equalsIgnoreCase(login.getUsername())).isPresent();
		default -> throw new IllegalArgumentException("Unexpected value: " + role);
		};
	}

	@Override
	public String entitySimpleClassName() {
		return "player";
	}


}
