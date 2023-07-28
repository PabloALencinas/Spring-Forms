package com.pabloagustin.springbootform.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RequeridoValidador implements ConstraintValidator<Requerido, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
		if(value == null || value.isEmpty() || value.isBlank()){
			return false;
		}
		return true;
	}
}
