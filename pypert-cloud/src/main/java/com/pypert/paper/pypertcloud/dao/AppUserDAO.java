package com.pypert.paper.pypertcloud.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.pypert.paper.pypertcloud.model.AppUser;
import com.pypert.paper.pypertcloud.model.Gender;
import com.pypert.paper.pypertcloud.formbean.AppUserForm;

@Repository
public class AppUserDAO {
	@Autowired
	private PasswordEncoder passwordEncoder;

	private static final Map<Long, AppUser> USERS_MAP = new HashMap<>();
	static {
		initDATA();
	}

	private static void initDATA() {
		String encryptedPassword = "";
		AppUser sunga = new AppUser(1L, "sunga", "Kim", "Sunga", true, Gender.MALE, "sungakim816@gmail.com",
				encryptedPassword, "PH");
		USERS_MAP.put(sunga.getUserId(), sunga);
	}

	public Long getMaxUserId() {
		long max = 0;
		for (Long id : USERS_MAP.keySet()) {
			if (id > max) {
				max = id;
			}
		}
		return max;
	}

	public AppUser findAppUserByUserName(String userName) {
		Collection<AppUser> appUsers = USERS_MAP.values();
		for (AppUser appUser : appUsers) {
			if (appUser.getUserName().equals(userName)) {
				return appUser;
			}
		}
		return null;
	}

	public AppUser findAppUserByEmail(String email) {
		Collection<AppUser> appUsers = USERS_MAP.values();
		for (AppUser appUser : appUsers) {
			if (appUser.getEmail().equals(email)) {
				return appUser;
			}
		}
		return null;
	}

	public List<AppUser> getAppUsers() {
		List<AppUser> list = new ArrayList<>();
		list.addAll(USERS_MAP.values());
		return list;
	}

	public AppUser createAppUser(AppUserForm form) {
		Long userId = this.getMaxUserId() + 1;
		String encryptedPassword = this.passwordEncoder.encode(form.getPassword());
		AppUser user = new AppUser(userId, form.getUserName(), form.getFirstName(), form.getLastName(), false,
				form.getGender(), form.getEmail(), form.getCountryCode(), encryptedPassword);
		USERS_MAP.put(userId, user);
		return user;

	}
}
