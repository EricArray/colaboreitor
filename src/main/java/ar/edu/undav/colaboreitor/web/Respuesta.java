package ar.edu.undav.colaboreitor.web;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	public ResponseEntity<String> conStatus(HttpStatus status, String nombre, Object valor) throws JSONException {
		JSONObject r = new JSONObject();
		r.put("status", status.value());
		r.put("timestamp", Timestamp.valueOf(LocalDateTime.now()));
		r.put(nombre, valor);
		return ResponseEntity.status(status).body(r.toString());
	}

	public ResponseEntity<String> ok(String nombre, Object valor) throws JSONException {
		return conStatus(HttpStatus.OK, nombre, valor);
	}
	
	public ResponseEntity<String> requestError(String reason) throws JSONException {
		JSONObject r = new JSONObject();
		r.put("status", 400);
		r.put("timestamp", Timestamp.valueOf(LocalDateTime.now()));
		r.put("reason", reason);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r.toString());
	}

	public ResponseEntity<String> internalError(String reason) throws JSONException {
		JSONObject r = new JSONObject();
		r.put("status", 500);
		r.put("timestamp", Timestamp.valueOf(LocalDateTime.now()));
		r.put("reason", reason);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(r.toString());
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
    	
    	return cuentaRepo.findByUsername(username);
	}
}
