package com.us1.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class User {
	private int id;
	private String name;
	private String email;
	private String username;
	private String password;
	
	private String roles;

}