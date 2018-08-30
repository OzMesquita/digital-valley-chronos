package br.ufc.russas.n2s.chronos.model.exceptions;

import java.io.Serializable;

public class IllegalCodeException extends IllegalArgumentException implements Serializable {
	public IllegalCodeException() {
	}

	public IllegalCodeException(String message) {
		super(message);
	}
}