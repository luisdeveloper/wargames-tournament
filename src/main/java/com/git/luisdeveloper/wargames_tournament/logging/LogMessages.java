package com.git.luisdeveloper.wargames_tournament.logging;

public final class LogMessages {

	private LogMessages() {
		throw new AssertionError("Utility class");
	}

	// [NIVEL] + verbo HTTP + endpoint – acción + entidad (+ detalle contextual)
	public static final String LOG_REQUEST_RECEIVED = "Request received: {}";
	public static final String LOG_RESPONSE_SENT = "Response sent successfully";
	public static final String LOG_ERROR_OCCURRED = "Error occurred: {}";
	public static final String LEVEL_REQUEST = "[REQUEST]";
	public static final String LEVEL_SUCCESS = "[SUCCESS]";
	public static final String LEVEL_WARNING = "[WARNING]";
	public static final String LEVEL_ERROR = "[ERROR]";
	public static final String LEVEL_CRITICAL_ERROR = "[SEVERE]";
	public static final String CATEGORY_CONTROLLER = "[CONTROLLER]";
	public static final String CATEGORY_SERVICE = "[SERVICE]";
	public static final String CATEGORY_SECURITY = "[SECURITY]";
	public static final String HTTP_GET = "GET";
	public static final String HTTP_POST = "POST";
	public static final String HTTP_PUT = "PUT";
	public static final String HTTP_PATCH = "PATCH";
	public static final String HTTP_DELETE = "DELETE";
}
