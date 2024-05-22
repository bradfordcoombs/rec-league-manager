package dev.bc.sas.security;

class PermissionUtil {

	private PermissionUtil() {
	}

	static boolean isWritePermission(Object permission) {
		return permission.toString().trim().equalsIgnoreCase("write");
	}
}
