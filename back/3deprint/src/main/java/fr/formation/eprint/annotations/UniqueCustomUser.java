package fr.formation.eprint.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueCustomUserValidator.class)
public @interface UniqueCustomUser {

	String message() default "Surnom déjà pris!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
