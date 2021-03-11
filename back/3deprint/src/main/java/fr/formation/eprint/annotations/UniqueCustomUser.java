package fr.formation.eprint.annotations;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = UniqueCustomUserValidator.class)
public @interface UniqueCustomUser {

	String message() default "Surnom déjà pris!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
