package dev.bc.sas.domain;

import dev.bc.sas.web.PlayerRequestModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
record Player(

		@Id @GeneratedValue(strategy = GenerationType.AUTO) Long id,

		String firstName,

		String lastName,

		@Column(unique = true, nullable = false) String email,

		@ManyToOne Team team
) {

	static Player init(PlayerRequestModel playerModel) {
		return new Player(null, playerModel.firstName(), playerModel.lastName(), playerModel.email(), null);
	}

	Player update(PlayerRequestModel playerRequestModel) {
		return new Player(this.id, playerRequestModel.firstName(), playerRequestModel.lastName(),
				playerRequestModel.email(), team
				);
	}

	Player assignTo(Team team) {
		return new Player(this.id, this.firstName, this.lastName, this.email, team);
	}
}
