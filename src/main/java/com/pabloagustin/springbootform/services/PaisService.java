package com.pabloagustin.springbootform.services;

import com.pabloagustin.springbootform.models.Pais;

import java.util.List;

public interface PaisService {

	public List<Pais> listar();

	public Pais obtenerPorId(Integer id);
}
