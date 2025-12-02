package com.git.luisdeveloper.wargames_tournament.logging;

import static com.git.luisdeveloper.wargames_tournament.logging.LogMessages.CATEGORY_CONTROLLER;

import org.springframework.stereotype.Component;;

@Component
public class ControllerLogFormatter extends LogFormatter {

	@Override
	protected String category() {
		return CATEGORY_CONTROLLER;
	}

	
	
}
