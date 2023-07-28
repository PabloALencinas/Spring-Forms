package com.pabloagustin.springbootform.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IdentificadorRegexValidador.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD})
public @interface IdentificadorRegex {
	String message() default "Identificador Invalido!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
