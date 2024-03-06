package com.us1.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.us1.filter.JwtProvider;
import com.us1.mapper.UserMapper;
import com.us1.model.User;
import com.us1.pojo.AuthRequest;
import com.us1.pojo.LoginResponse;
import com.us1.service.CustomUserDetails;
import com.us1.utils.ApplicationResponseConstants;
import com.us1.utils.ApplicationUriConstant;
import com.us1.utils.ErrorDataEnum;
import com.us1.utils.GenericResponse;
import com.us1.utils.SuccessMsgEnum;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = ApplicationUriConstant.API + ApplicationUriConstant.V1)
public class DemoController extends BaseController {

	@Autowired
	private UserMapper mapper;

	@GetMapping(ApplicationUriConstant.GET_ALL_USERS)
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<User> users(HttpServletRequest httpServletRequest) {
	
		return mapper.findAllUsers();
	}

	@PostMapping(ApplicationUriConstant.LOGIN)
	public ResponseEntity<Object> signinResponse(@RequestBody AuthRequest loginRequest) throws Exception {

		LoginResponse response = new LoginResponse();

		User user = mapper.findByUsername(loginRequest.getUsername());
		if (user == null) {
			return ResponseEntity
					.ok(getCommonServices().generateBadResponseWithMessageKey(ErrorDataEnum.INVALID_USER.getCode()));
		}

		if (!getCommonServices().matchesWithBcrypt(loginRequest.getPassword(), user.getPassword())) {
			return ResponseEntity.ok(getCommonServices()
					.generateBadResponseWithMessageKey(ErrorDataEnum.INVALID_CREDENTIALS_PASSWORD.getCode()));
		}

		final Authentication authentication = new UsernamePasswordAuthenticationToken(new CustomUserDetails(user),
				new SimpleGrantedAuthority(user.getRoles()));
		
	
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = JwtProvider.generateToken(authentication);
		
			
		BeanUtils.copyProperties(user, response);

		response.setLoginToken(token);

		return ResponseEntity.ok(new GenericResponse(ApplicationResponseConstants.SUCCESS_RESPONSE,
				getCommonServices().getMessageByCode(SuccessMsgEnum.LOGIN_SUCCESS.getCode()), response));

	}

}
