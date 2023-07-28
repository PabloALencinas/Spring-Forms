package com.pabloagustin.springbootform.validation;

import com.pabloagustin.springbootform.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UsuarioValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		//Usuario usuario = (Usuario)target;

		// Validamos que NOMBRE no sea vacio
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotEmpty.usuario.nombre");

		// Validamos el IDENTIFICADOR
//		if(!usuario.getIdentificador().matches("[0-9]{5}[-][A-Z]{1}")){
//			errors.rejectValue("identificador", "pattern.usuario.identificador");
//		}
	}
}
