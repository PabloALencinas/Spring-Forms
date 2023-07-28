package com.pabloagustin.springbootform.services;

import com.pabloagustin.springbootform.models.Pais;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PaisServiceImplementation implements PaisService{

	private List<Pais> lista;
	public PaisServiceImplementation(){
		this.lista = Arrays.asList(
				new Pais(1, "AR", "Argentina"),
				new Pais(2, "MX", "Mexico"),
				new Pais(3, "CH", "Chile"),
				new Pais(4, "CO", "Colombia"),
				new Pais(5, "PE", "Peru"),
				new Pais(6, "VE", "Venezuela"));
	}
	@Override
	public List<Pais> listar() {
		return lista;
	}

	@Override
	public Pais obtenerPorId(Integer id) {
		Pais resultado = null;
		for (Pais pais: this.lista) {
			if(id == pais.getId()){
				resultado = pais;
				break;
			}
		}
		return resultado;
	}
}
