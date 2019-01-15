package com.pypert.paper.pypertcloud.model;

import lombok.Data;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class AppUser {
	private Long userId;
	private String userName;
	private String firstName;
	private String lastName;
	private boolean enabled;
	private String gender;
	private String email;
	private String encryptedPassword;
	private String countryCode;

	public AppUser() {

	}
}
