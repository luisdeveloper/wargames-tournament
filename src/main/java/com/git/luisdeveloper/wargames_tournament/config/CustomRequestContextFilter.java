package com.git.luisdeveloper.wargames_tournament.config;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomRequestContextFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			MDC.put("requestId", UUID.randomUUID().toString());
			MDC.put("method", request.getMethod());
			MDC.put("uri", request.getRequestURI());
			filterChain.doFilter(request, response);
		}finally {
			MDC.clear();
		}
		
	}

}
