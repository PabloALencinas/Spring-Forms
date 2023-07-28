package com.pabloagustin.springbootform.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

// Debe implementar HandlerInterceptor

@Component("tiempoTranscurridoInterceptor")
public class TiempoTranscurridoInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(TiempoTranscurridoInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		// Si queremos que el tiempo transcurrido solo se muestre en el request de GET no en POST por ejemplo.
		if(request.getMethod().equalsIgnoreCase("post")) {
			return true;
		}

		if(handler instanceof HandlerMethod){
			HandlerMethod metodo = (HandlerMethod) handler;
			logger.info("Es un metodo del controlador: " + metodo.getMethod().getName());
		}
		logger.info("TiempoTranscurridoInterceptor: preHandle() entrando...");
		long tiempoInicio = System.currentTimeMillis();
		request.setAttribute("tiempoInicio", tiempoInicio);

		// Emulando una demora, una sobrecarga
		Random random = new Random();
		Integer demora = random.nextInt(500);
		Thread.sleep(demora);

		// Ejemplo si retornamos false desde el interceptor
		// Podemos redirigir a una pagina por si el retorno es falo, ejemplo, login
		// Redirige a esta pagina cuando falla la respuesta del interceptor

		// response.sendRedirect(request.getContextPath().concat("/login"));
		// return false

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

		// Si queremos que el tiempo transcurrido solo se muestre en el request de GET no en POST por ejemplo.
		if(request.getMethod().equalsIgnoreCase("post")) {
			return;
		}

		long tiempoFin = System.currentTimeMillis();
		long tiempoInicio = (Long) request.getAttribute("tiempoInicio");
		long tiempoTranscurrido = tiempoFin - tiempoInicio;

		if(modelAndView != null){
			modelAndView.addObject("tiempoTranscurrido", tiempoTranscurrido);
		}

		logger.info("Tiempo Transcurrido: " + tiempoTranscurrido + " milisegundos");

		logger.info("TiempoTranscurridoInterceptor: postHandle() saliendo...");

	}
}
