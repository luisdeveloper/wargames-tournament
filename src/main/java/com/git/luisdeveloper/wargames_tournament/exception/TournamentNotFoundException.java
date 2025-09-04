package com.git.luisdeveloper.wargames_tournament.exception;

public class TournamentNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5779021150955307658L;

	public TournamentNotFoundException() {
		super();
	}

	public TournamentNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TournamentNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public TournamentNotFoundException(String message) {
		super(message);
	}

	public TournamentNotFoundException(Throwable cause) {
		super(cause);
	}

}
