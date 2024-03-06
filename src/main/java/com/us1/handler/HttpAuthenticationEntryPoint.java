package com.us1.handler;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.us1.utils.ApplicationResponseConstants;
import com.us1.utils.CommonServices;
import com.us1.utils.ErrorDataEnum;
import com.us1.utils.GenericResponse;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("httpAuthenticationEntryPoint")
public class HttpAuthenticationEntryPoint implements org.springframework.security.web.AuthenticationEntryPoint {

	@Autowired
	private CommonServices commonServices;

	@Override
	public void commence(final HttpServletRequest request, final HttpServletResponse response,
			final AuthenticationException authException) throws IOException, ServletException {

		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

		final PrintWriter printWriter = response.getWriter();

		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			response.setStatus(HttpServletResponse.SC_OK);

		} else {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			objectMapper().writeValue(printWriter, new GenericResponse(ApplicationResponseConstants.UNAUTHORIZED_401,
					commonServices.getMessageByCode(ErrorDataEnum.USER_HAVENT_AUT.getCode())));

		}
		printWriter.flush();

	}

	@Bean
	public ObjectMapper objectMapper() {

		return new ObjectMapper();
	}

}