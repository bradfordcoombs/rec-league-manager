package dev.bc.sas.web;

import dev.bc.sas.validation.PlayerId;
import dev.bc.sas.validation.TeamId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PlayerRequestModel(
		@PlayerId(nullable = true) Long id,

		@NotBlank @Size(max = 255) String firstName,

		@NotBlank @Size(max = 255) String lastName,

		@NotBlank @Size(max = 255) String email,

		@TeamId(nullable = true) Long teamId) {
}
