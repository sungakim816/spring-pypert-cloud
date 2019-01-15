package com.pypert.paper.pypertcloud.formbean;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class AppUserForm {
	private Long userId;
	private String userName;
	private String firstName;
	private String lastName;
	private boolean enabled;
	private String gender;
	private String email;
	private String password;
	private String confirmPassword;
	private String countryCode;

	public AppUserForm() {

	}
}
