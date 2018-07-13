package ar.edu.undav.colaboreitor.web;

import org.json.JSONException;
import org.json.JSONObject;

public class Respuesta {
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
}
