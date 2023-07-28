package com.pabloagustin.springbootform.editors;

import com.pabloagustin.springbootform.services.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;
// Component para poder Inyectar y REGISTRAR EN EL INITBINDER
@Component
public class PaisPropertyEditor extends PropertyEditorSupport {

	@Autowired
	private PaisService service;

	@Override
	public void setAsText(String idString) throws IllegalArgumentException {

			try{
				Integer id = Integer.parseInt(idString);
				this.setValue(service.obtenerPorId(id));
			} catch (NumberFormatException e){
				setValue(null);
			}

	}
}
