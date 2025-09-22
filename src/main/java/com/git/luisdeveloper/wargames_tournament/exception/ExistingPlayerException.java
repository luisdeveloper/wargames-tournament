package com.git.luisdeveloper.wargames_tournament.exception;

public class ExistingPlayerException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1912759642753442077L;

	public ExistingPlayerException() {
		super();
	}

	public ExistingPlayerException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ExistingPlayerException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExistingPlayerException(String message) {
		super(message);
	}

	public ExistingPlayerException(Throwable cause) {
		super(cause);
	}

}
