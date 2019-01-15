package com.pypert.paper.pypertcloud.validator;

import org.springframework.stereotype.Component;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import com.pypert.paper.pypertcloud.dao.AppUserDAO;
import com.pypert.paper.pypertcloud.formbean.AppUserForm;
import com.pypert.paper.pypertcloud.model.AppUser;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AppUserValidator implements Validator {
	private EmailValidator emailValidator = EmailValidator.getInstance();
	@Autowired
	private AppUserDAO appUserDAO;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == AppUserForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		AppUserForm appUserForm = (AppUserForm) target;
		// Check the fields of AppUserForm
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.appUserForm.userName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.appUserForm.firstName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.appUserForm.lastName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.appUserForm.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.appUserForm.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.appUserForm.confirmPassword");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "NotEmpty.appUserForm.gender");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "countryCode", "NotEmpty.appUserForm.countryCode");
		if (!this.emailValidator.isValid(appUserForm.getEmail())) {
			errors.rejectValue("email", "Pattern.appUserForm.email");
		} else if (appUserForm.getUserId() == null) {
			AppUser dbUser = appUserDAO.findAppUserByEmail(appUserForm.getEmail());
			if (dbUser != null) {
				errors.rejectValue("email", "Duplicate.appUserForm.email");
			}
		}

		if (!errors.hasFieldErrors("userName")) {
			AppUser dbUser = appUserDAO.findAppUserByUserName(appUserForm.getUserName());
			if (dbUser != null) {
				// User name is not available / already been taken
				errors.rejectValue("userName", "Duplicate.appUserForm.userName");
			}
		}

		// if passwords are not much
		if (!errors.hasErrors()) {
			if (!appUserForm.getConfirmPassword().equals(appUserForm.getPassword())) {
				errors.rejectValue("confirmPassword", "Match.appUserForm.confirmPassword");
			}
		}
	}
}
