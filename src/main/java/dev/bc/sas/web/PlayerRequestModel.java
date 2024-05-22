package dev.bc.sas.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PlayerRequestModel(

		@NotBlank @Size(max = 255) String firstName,

		@NotBlank @Size(max = 255) String lastName,

		@NotBlank @Size(max = 255) String email) {
}
