package com.git.luisdeveloper.wargames_tournament.logging;

import static com.git.luisdeveloper.wargames_tournament.logging.LogMessages.CATEGORY_SERVICE;

import org.springframework.stereotype.Component;

@Component
public class ServiceLogFormatter extends LogFormatter{
	@Override
	protected String category() {
		return CATEGORY_SERVICE;
	}
}
