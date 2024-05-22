package dev.bc.sas.validation;

import org.springframework.stereotype.Component;

import dev.bc.sas.domain.PlayerService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class PlayerIdValidator implements ConstraintValidator<PlayerId, Long> {

	private final PlayerService playerService;
	private boolean nullable;

	public PlayerIdValidator(PlayerService playerService) {
		this.playerService = playerService;
	}

	@Override
	public void initialize(PlayerId annotation) {
		this.nullable = annotation.nullable();
	}

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		return (value == null && this.nullable) || playerService.getPlayer(value).isPresent();
	}

}
