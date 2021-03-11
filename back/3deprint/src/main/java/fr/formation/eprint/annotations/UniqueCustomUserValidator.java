package fr.formation.eprint.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import fr.formation.eprint.services.CustomUserService;

public class UniqueCustomUserValidator implements ConstraintValidator<UniqueCustomUser, String> {

	private CustomUserService customUserService;

	public UniqueCustomUserValidator(CustomUserService customUserService) {
		this.customUserService = customUserService;
	}

	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		return customUserService.isUsernameValid(username);
	}
}
