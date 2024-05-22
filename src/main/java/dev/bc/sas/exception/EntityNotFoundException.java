package dev.bc.sas.exception;

public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String identifier) {
		super(identifier);
	}

}
