package ar.edu.undav.colaboreitor.web;

import org.json.JSONException;
import org.json.JSONObject;

public class Respuesta {
	public String ok(String nombre, Object valor) throws JSONException {
		JSONObject r = new JSONObject();
		r.put("status", "ok");
		r.put(nombre, valor);
		return r.toString();
	}

	public String error(String reason) {
		final String template = "{\"status\":\"error\",\"reason\":\"%s\"}";
		return String.format(template, reason);
	}
}
