package dev.bc.sas.domain;

import dev.bc.sas.web.CoachModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
record Coach(
		
		@Id @GeneratedValue(strategy = GenerationType.AUTO) Long id,

		String firstName,

		String lastName,

		@Column(length = 8)
		String phone,

		@Column(nullable = false, unique = true)
		String email
) {

	static Coach init(CoachModel coachModel) {
		return new Coach(null, coachModel.firstName(), coachModel.lastName(), coachModel.phone(), coachModel.email());
	}

	Coach update(CoachModel coachModel) {
		return new Coach(id, coachModel.firstName(), coachModel.lastName(), coachModel.phone(), coachModel.email());
	}
}