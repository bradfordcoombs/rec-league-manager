package dev.bc.sas.validation;

import org.springframework.stereotype.Component;

import dev.bc.sas.domain.CoachService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class CoachIdValidator implements ConstraintValidator<CoachId, Long> {

	private final CoachService coachService;

	public CoachIdValidator(CoachService coachService) {
		this.coachService = coachService;
	}

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		return coachService.getCoach(value).isPresent();
	}

}
