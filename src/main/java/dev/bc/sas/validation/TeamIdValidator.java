package dev.bc.sas.validation;

import org.springframework.stereotype.Component;

import dev.bc.sas.domain.TeamService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class TeamIdValidator implements ConstraintValidator<TeamId, Long> {

	private final TeamService teamService;
	private boolean nullable;

	public TeamIdValidator(TeamService teamService) {
		this.teamService = teamService;
	}

	@Override
	public void initialize(TeamId annotation) {
		this.nullable = annotation.nullable();
	}

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		return (value == null && this.nullable) || teamService.getTeam(value).isPresent();
	}

}
