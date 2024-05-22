package dev.bc.sas.security;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.lang.NonNull;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

class CustomPermissionEvaluator implements PermissionEvaluator {

	private final Map<String, EntityPermissionEvaluator> evaluators;

	public CustomPermissionEvaluator(List<EntityPermissionEvaluator> permissionEvaluators) {
		this.evaluators = permissionEvaluators.stream()
				.collect(Collectors.toMap(evaluator -> evaluator.entitySimpleClassName().toLowerCase(),
						evaluator -> evaluator));
	}

	@Override
	public boolean hasPermission(Authentication authentication, @NonNull Object targetDomainObject,
			@NonNull Object permission) {
		var evaluator = evaluators.get(targetDomainObject.getClass().getSimpleName().toLowerCase());
		return evaluator != null && evaluator.hasPermission(authentication, targetDomainObject, permission);
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, @NonNull String targetType,
			@NonNull Object permission) {
		var evaluator = evaluators.get(targetType.toLowerCase());
		return evaluator != null && evaluator.hasPermission(authentication, targetId, targetType, permission);
	}
}
