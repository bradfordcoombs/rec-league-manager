package dev.bc.sas.security;

import org.springframework.security.core.userdetails.UserDetails;

public class PermissionUtil {

	private PermissionUtil() {
	}

	public static boolean isWritePermission(Object permission) {
		return permission.toString().trim().equalsIgnoreCase("write");
	}

	public static Role getRole(UserDetails userDetails) {
		return Role.valueOf(userDetails.getAuthorities().iterator().next().getAuthority());
	}
}
