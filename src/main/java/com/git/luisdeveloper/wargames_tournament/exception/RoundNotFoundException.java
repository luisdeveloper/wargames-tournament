package com.git.luisdeveloper.wargames_tournament.exception;

public class RoundNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5485149450351234912L;

	public RoundNotFoundException() {
		super();
	}

	public RoundNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RoundNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public RoundNotFoundException(String message) {
		super(message);
	}

	public RoundNotFoundException(Throwable cause) {
		super(cause);
	}

}
