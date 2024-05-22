package dev.bc.sas.validation;

import java.util.List;

import org.springframework.stereotype.Component;

import dev.bc.sas.domain.PlayerService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class PlayerIdListValidator implements ConstraintValidator<PlayerId, List<Long>> {

	private final PlayerService playerService;

	public PlayerIdListValidator(PlayerService playerService) {
		this.playerService = playerService;
	}

	@Override
	public boolean isValid(List<Long> value, ConstraintValidatorContext context) {
		return playerService.getPlayers(value).size() == value.size();
	}

}
