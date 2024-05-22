package dev.bc.sas.security;

import org.springframework.security.core.userdetails.UserDetails;

class PermissionUtil {

	private PermissionUtil() {
	}

	static boolean isWritePermission(Object permission) {
		return permission.toString().trim().equalsIgnoreCase("write");
	}

	static Role getRole(UserDetails userDetails) {
		return Role.valueOf(userDetails.getAuthorities().iterator().next().getAuthority());
	}
}
