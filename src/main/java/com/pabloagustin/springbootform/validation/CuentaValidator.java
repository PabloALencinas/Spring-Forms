package com.pabloagustin.springbootform.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CuentaValidator implements ConstraintValidator<Cuenta, Integer> {

	@Override
	public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {

		if(integer == null || integer < 5 || integer > 5000){
			return false;
		}
		return true;
	}
}
