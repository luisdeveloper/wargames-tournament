package com.git.luisdeveloper.wargames_tournament.logging;

import static com.git.luisdeveloper.wargames_tournament.logging.LogMessages.LEVEL_SUCCESS;
import static com.git.luisdeveloper.wargames_tournament.logging.LogMessages.LEVEL_WARNING;

import org.slf4j.MDC;

import static com.git.luisdeveloper.wargames_tournament.logging.LogMessages.LEVEL_REQUEST;
import static com.git.luisdeveloper.wargames_tournament.logging.LogMessages.LEVEL_ERROR;
import static com.git.luisdeveloper.wargames_tournament.logging.LogMessages.LEVEL_CRITICAL_ERROR;

public abstract class LogFormatter {

	protected abstract String category();

	protected String format(String level, String message) {
		return String.format("%s %s %s %s - %s", level, category(), MDC.get("method"), MDC.get("uri"), message);
	}

	public String request(String msg) {
		return format(LEVEL_REQUEST, msg);
	}

	public String success(String msg) {
		return format(LEVEL_SUCCESS, msg);
	}

	public String warning(String msg) {
		return format(LEVEL_WARNING, msg);
	}

	public String error(String msg) {
		return format(LEVEL_ERROR, msg);
	}

	public String criticalError(String msg) {
		return format(LEVEL_CRITICAL_ERROR, msg);
	}

}