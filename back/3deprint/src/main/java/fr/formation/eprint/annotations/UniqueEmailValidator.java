package fr.formation.eprint.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import fr.formation.eprint.services.CustomUserService;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
	private CustomUserService customUserService;

	public UniqueEmailValidator(CustomUserService customUserService) {
		this.customUserService = customUserService;
	}

	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		return customUserService.isEmailValid(username);
	}
}
