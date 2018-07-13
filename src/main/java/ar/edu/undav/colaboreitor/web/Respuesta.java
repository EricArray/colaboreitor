package ar.edu.undav.colaboreitor.web;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ar.edu.undav.colaboreitor.domain.Cuenta;
import ar.edu.undav.colaboreitor.repository.CuentaRepo;

@Component
public class Respuesta {
	@Autowired
	public CuentaRepo cuentaRepo;
	
	public String ok(String nombre, Object valor) throws JSONException {
		JSONObject r = new JSONObject();
		r.put("status", 200);
		r.put(nombre, valor);
		return r.toString();
	}

	public String created(String nombre, Object valor) throws JSONException {
		JSONObject r = new JSONObject();
		r.put("status", 201);
		r.put(nombre, valor);
		return r.toString();
	}

	public String requestError(String reason) {
		final String template = "{\"status\":400,\"reason\":\"%s\"}";
		return String.format(template, reason);
	}

	public String internalError(String reason) {
		final String template = "{\"status\":500,\"reason\":\"%s\"}";
		return String.format(template, reason);
	}
	
	public Cuenta getCuenta() {
		SecurityContext context = SecurityContextHolder.getContext();
		if (context == null) return null;
		
    	Authentication auth = context.getAuthentication();
    	if (auth == null) return null;
    	
    	Object principal = auth.getPrincipal();
    	if (principal == null) return null;
    	
    	String username = principal.toString();
    	if (username == null) return null;
    	
    	Cuenta cuenta = cuentaRepo.findByUsername(username);
    	return cuenta;
	}
}
