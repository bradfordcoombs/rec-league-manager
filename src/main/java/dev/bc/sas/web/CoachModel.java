package dev.bc.sas.web;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;

public record CoachModel(

		@Size(max = 255)
		String firstName,

		@Size(max = 255)
		String lastName,

		@Column(length = 8)
		String phone,

		@Size(max = 255)
		String email
) {
}