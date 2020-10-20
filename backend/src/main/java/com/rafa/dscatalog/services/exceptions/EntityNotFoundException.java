package com.rafa.dscatalog.services.exceptions;

public class EntityNotFoundException extends RuntimeException {
// Pode extender de Exception tb. Mas exception vai te obrigar a colocar dentro de um try-catch. Runtime nao.

	private static final long serialVersionUID = 1596355923467709028L;
	
	public EntityNotFoundException(String msg) {
		super(msg);
	}

}
