package dev.bc.sas.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private final Long id;

	@Column(nullable = false)
	private final String name;

	public Team(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	@SuppressWarnings("unused")
	private Team() {
		this(null, null);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
