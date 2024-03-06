package com.us1.pojo;

import lombok.Data;

@Data
public class LoginResponse {
	
	private int id;
	private String name;
	private String username;
	private String email;
	private String loginToken;
	

}
