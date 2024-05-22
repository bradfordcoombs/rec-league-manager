package dev.bc.sas.domain;

import dev.bc.sas.web.PlayerRequestModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id;

	private final String firstName;

	private final String lastName;

	@Column(unique = true, nullable = false)
	private final String email;

	@ManyToOne
	private final Team team;

	Player(Long id, String firstName, String lastName, String email, Team team) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.team = team;
	}

	Player update(PlayerRequestModel playerRequestModel) {
		return new Player(this.id, playerRequestModel.firstName(), playerRequestModel.lastName(),
				playerRequestModel.email(), team
				);
	}

	Player assignTo(Team team) {
		return new Player(this.id, this.firstName, this.lastName, this.email, team);
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public Team getTeam() {
		return team;
	}

}
