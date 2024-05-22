package dev.bc.sas.security;

import org.springframework.security.access.PermissionEvaluator;

public interface EntityPermissionEvaluator extends PermissionEvaluator {
	String entitySimpleClassName();

}
