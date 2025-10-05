package com.git.luisdeveloper.wargames_tournament.exceptions;

public class NoPendingRoundsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3101043410554963608L;

	public NoPendingRoundsException() {
		super();
	}

	public NoPendingRoundsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoPendingRoundsException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoPendingRoundsException(String message) {
		super(message);
	}

	public NoPendingRoundsException(Throwable cause) {
		super(cause);
	}

}
