package com.pabloagustin.springbootform.services;

import com.pabloagustin.springbootform.models.Role;
import java.util.List;

public interface RoleService {

	public List<Role> listar();
	public Role obtenerPorId(Integer id);
}
