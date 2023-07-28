package com.pabloagustin.springbootform.validation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CuentaValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD})
public @interface Cuenta{
	String message() default "Valor en cuenta invalido.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
