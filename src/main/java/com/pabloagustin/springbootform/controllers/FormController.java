package com.pabloagustin.springbootform.controllers;

import com.pabloagustin.springbootform.editors.NombreMayusculaEditor;
import com.pabloagustin.springbootform.editors.PaisPropertyEditor;
import com.pabloagustin.springbootform.editors.RolesEditor;
import com.pabloagustin.springbootform.models.Pais;
import com.pabloagustin.springbootform.models.Role;
import com.pabloagustin.springbootform.models.Usuario;
import com.pabloagustin.springbootform.services.PaisService;
import com.pabloagustin.springbootform.services.RoleService;
import com.pabloagustin.springbootform.validation.UsuarioValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.text.SimpleDateFormat;
import java.util.*;


@Controller
// Se mantienen de forma persistente
@SessionAttributes("usuario")
public class FormController {

	// INYECTAMOS el validador de usuario
	@Autowired
	private UsuarioValidator validador;

	@Autowired
	private PaisService paisService;

	@Autowired
	private PaisPropertyEditor paisEditor;

	@Autowired
	private RoleService roleService;

	@Autowired
	private RolesEditor rolesEditor;

	// InitBinder para registrar el validador
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.addValidators(validador);

		// Convertimos la fecha para nuestro propio formato
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// Que no sea tolerante, para evitar que coloquen fechas de formato distinto. Evitar ambiguedad!
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "fechaNacimiento", new CustomDateEditor(dateFormat, false));

		// Registrar nuestro editor de Mayus
		binder.registerCustomEditor(String.class, "nombre" , new NombreMayusculaEditor());
		binder.registerCustomEditor(String.class, "apellido" , new NombreMayusculaEditor());

		// Registramos el paisEditor
		binder.registerCustomEditor(Pais.class, "pais", paisEditor);

		// Registramos el roleEditor
		binder.registerCustomEditor(Role.class, "roles", rolesEditor);
	}

	@ModelAttribute("genero")
	public List<String> genero(){
		return Arrays.asList("Hombre", "Mujer");
	}

	@ModelAttribute("listaRoles")
	public List<Role> listRoles(){
		return this.roleService.listar();
	}

	@ModelAttribute("listaRolesString")
	public List<String> listarRolesString(){
		List<String> roles = new ArrayList<>();
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");
		roles.add("ROLE_MODERATOR");
		return roles;
	}

	@ModelAttribute("listRolesMap")
	public Map<String, String> listRolesMap(){
		Map<String, String> roles = new HashMap<String, String>();
		roles.put("ROLE_ADMIN", "Administrador");
		roles.put("ROLE_USER", "Usuario");
		roles.put("ROLE_MODERATOR", "Moderador");

		return roles;
	}

	@ModelAttribute("listaPaises")
	public List<Pais> listaPaises(){
		return paisService.listar();
	}

	@ModelAttribute("paises")
	public List<String> paises(){
		return Arrays.asList("Argentina", "Mexico", "Chile", "Colombia", "Peru", "Venezuela");
	}

	@ModelAttribute("paisesMap")
	public Map<String, String> paisesMap(){
		Map<String, String> paises = new HashMap<String, String>();
		paises.put("AR", "Argentina");
		paises.put("MX", "Mexico");
		paises.put("CH", "Chile");
		paises.put("CO", "Colombia");
		paises.put("PE", "Peru");
		paises.put("VE", "Venezuela");
		return paises;
	}

	// 2 metodos handler -> Mostrar Formulario (GET) y Procesar Formulario (POST)
	@GetMapping("/form")
	public String form(Model model){
		Usuario usuario = new Usuario();
		usuario.setIdentificador("15560-F");
		usuario.setHabilitar(true);
		usuario.setValorSecreto("Algun valor secreto ****");
		model.addAttribute("titulo", "Formulario de Usuarios" );
		model.addAttribute("usuario", usuario);
		return "form";
	}

	@PostMapping("/form")
	// EL BINDING RESULT SIEEEMPRE TIENE QUE ESTAR DESPUES DEL OBJETO QUE SE VALIDA! No despues de MODEL TAMPOCO!
	public String procesar(@Valid Usuario usuario, BindingResult result, Model model){

		//validador.validate(usuario, result);

		if (result.hasErrors()){
			model.addAttribute("titulo", "Resultado del formulario" );
			return "form";
		}

		return "redirect:/ver";
	}

	// Aca se va a manejar el resultado del envio de resultado para no enviar por duplicado despues de un refresh del form
	// De arriba
	@GetMapping("/ver")
	public String ver(@SessionAttribute(name = "usuario", required = false) Usuario usuario, Model model, SessionStatus status){

		if (usuario == null){
			return "redirect:/form";
		}
		model.addAttribute("titulo", "Resultado del formulario" );

		status.setComplete();
		return "resultado";
	}

}
