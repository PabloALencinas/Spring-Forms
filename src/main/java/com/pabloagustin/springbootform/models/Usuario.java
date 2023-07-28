package com.pabloagustin.springbootform.models;


// Objeto ENTITY, no es necesario INYECTAR en el controlador

import com.pabloagustin.springbootform.validation.IdentificadorRegex;
import com.pabloagustin.springbootform.validation.Requerido;
import jakarta.validation.constraints.*;

import java.util.Date;

public class Usuario {
	// Validacion!

	//@Pattern(regexp = "[0-9]{5}[-][A-Z]{1}", message = "Debe coincidir con el formato 5 ENTEROS, GUION, 1 LETRA MAYUS")
	@IdentificadorRegex
	private String identificador;
	@NotBlank
	@Size(min = 3, max = 25)
	private String username;
	@NotEmpty
	private String password;
	@Requerido
	@Email
	private String email;

	// Agregamos estos ATRIBUTOS para mostrar en el formulario
	//@NotEmpty
	@Requerido
	private String nombre;
	//@NotEmpty
	@Requerido
	private String apellido;

	// Not null para objetos no para datos primitivos

	//@Cuenta
	@NotNull
	@Min(5)
	@Max(5000)
	private Integer cuenta;

	// Fecha

	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past
	@NotNull
	private Date fechaNacimiento;

	@NotNull
	private Pais pais;

	@NotNull
	private Role roles;

	private boolean habilitar;

	@NotNull
	private String genero;

	private String valorSecreto;


	// Getters y Setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public Integer getCuenta() {
		return cuenta;
	}

	public void setCuenta(Integer cuenta) {
		this.cuenta = cuenta;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Role getRoles() {
		return roles;
	}

	public void setRoles(Role roles) {
		this.roles = roles;
	}

	public boolean isHabilitar() {
		return habilitar;
	}

	public void setHabilitar(boolean habilitar) {
		this.habilitar = habilitar;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getValorSecreto() {
		return valorSecreto;
	}

	public void setValorSecreto(String valorSecreto) {
		this.valorSecreto = valorSecreto;
	}
}
