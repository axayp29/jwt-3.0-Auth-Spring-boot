package com.us1.filter;

import java.io.IOException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.us1.service.CustomUserServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter {

	public static String JWT_Header = "Authorization";

	private CustomUserServiceImpl customUserServiceImpl;

	public JwtFilter(CustomUserServiceImpl customUserServiceImpl) {
		super();
		this.customUserServiceImpl = customUserServiceImpl;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String jwt = request.getHeader(JWT_Header);

		if (jwt != null) {
			try {

				String username = JwtProvider.getUsernameFromToken(jwt);

				UserDetails userDetails = customUserServiceImpl.loadUserByUsername(username);

				Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null,
						userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);

			} catch (Exception exc) {

				//throw new BadCredentialsException("Invalid Token");
			}
		}

		filterChain.doFilter(request, response);

	}
}
