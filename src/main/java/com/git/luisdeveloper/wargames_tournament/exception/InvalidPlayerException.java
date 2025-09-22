package com.git.luisdeveloper.wargames_tournament.exception;

public class InvalidPlayerException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8835314768389988169L;

	public InvalidPlayerException() {
		super();
	}

	public InvalidPlayerException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidPlayerException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidPlayerException(String message) {
		super(message);
	}

	public InvalidPlayerException(Throwable cause) {
		super(cause);
	}
}
