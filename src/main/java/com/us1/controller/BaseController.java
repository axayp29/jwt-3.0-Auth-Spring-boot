package com.us1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import com.us1.mapper.UserMapper;
import com.us1.model.User;
import com.us1.service.CustomUserDetails;
import com.us1.utils.CommonServices;

import lombok.Getter;

@RestController
@Getter
public class BaseController {
	
	@Autowired
	private UserMapper mapper;
	
	@Autowired
	private CommonServices commonServices;

	public User getLoggedInUser() {
		final CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
			.getPrincipal();
		
		User user = mapper.findByUsername(userDetails.getUsername());
		
		return user;
	    }
}
