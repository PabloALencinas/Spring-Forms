package com.pabloagustin.springbootform.editors;

import com.pabloagustin.springbootform.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

// Component para poder Inyectar y REGISTRAR EN EL INITBINDER
@Component
public class RolesEditor extends PropertyEditorSupport {

	@Autowired
	private RoleService service;

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		try{
			Integer id = Integer.parseInt(text);
			setValue(service.obtenerPorId(id));
		} catch (NumberFormatException e){
			setValue(null);
		}
	}
}
