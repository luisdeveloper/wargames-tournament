package com.git.luisdeveloper.wargames_tournament.exception;

public class RoundAlreadyStartedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8708042846112493518L;

	public RoundAlreadyStartedException() {
		super();
	}

	public RoundAlreadyStartedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RoundAlreadyStartedException(String message, Throwable cause) {
		super(message, cause);
	}

	public RoundAlreadyStartedException(String message) {
		super(message);
	}

	public RoundAlreadyStartedException(Throwable cause) {
		super(cause);
	}

}
