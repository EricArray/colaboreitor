package ar.edu.undav.colaboreitor.web;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.undav.colaboreitor.domain.Cp;
import ar.edu.undav.colaboreitor.domain.Cuenta;
import ar.edu.undav.colaboreitor.domain.Foto;
import ar.edu.undav.colaboreitor.domain.Incidente;
import ar.edu.undav.colaboreitor.domain.Localidad;
import ar.edu.undav.colaboreitor.domain.Reaccion;
import ar.edu.undav.colaboreitor.repository.CpRepo;
import ar.edu.undav.colaboreitor.repository.FotoRepo;
import ar.edu.undav.colaboreitor.repository.IncidenteRepo;
import ar.edu.undav.colaboreitor.repository.LocalidadRepo;
import ar.edu.undav.colaboreitor.repository.ReaccionRepo;

@RestController
public class ReaccionController {

	@Autowired
	private ReaccionRepo reaccionRepo;

	@Autowired
	private IncidenteRepo incidenteRepo;
	
	@Autowired
	private Respuesta respuesta;
	
	JSONObject reaccionJson(Reaccion reaccion) throws JSONException {
        JSONObject json = new JSONObject();

        json.put("incidente", reaccion.getIncidente().getId());
        json.put("cuenta", reaccion.getCuenta().getId());
        json.put("reaccion", reaccion.getReaccion());
        
        return json;
	}

    @RequestMapping(value="/reaccion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String get() {
        System.out.println("GET /reaccion");
        
        try {
        	Cuenta cuenta = respuesta.getCuenta();
        	if (cuenta == null) {
        		return respuesta.internalError("Error interno al leer cuenta");
        	}
        	
            List<Reaccion> reacciones = reaccionRepo.findByCuenta(cuenta);
            
	        JSONObject[] json = new JSONObject[reacciones.size()];
	        int i = 0;
	        for (Reaccion reaccion : reacciones) {
	        	json[i] = reaccionJson(reaccion);
		        i += 1;
	        }
	        
        	return respuesta.ok("reaccion", json);
        } catch (JSONException e) {
			// TODO Auto-generated catch bcpk
			e.printStackTrace();
			return respuesta.internalError("Error interno al leer Reaccion");
		}
    }

    public static class PostBody {
    	public long incidente;
    	public int reaccion;
    	
		public PostBody() {
			super();
			// TODO Auto-generated constructor stub
		}

		public long getIncidente() {
			return incidente;
		}

		public void setIncidente(long incidente) {
			this.incidente = incidente;
		}

		public int getReaccion() {
			return reaccion;
		}

		public void setReaccion(int reaccion) {
			this.reaccion = reaccion;
		}
		
    }

    @RequestMapping(value="/reaccion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String post(@RequestBody PostBody body) {
        System.out.println("POST /reaccion");
        
        Optional<Incidente> opt = incidenteRepo.findById(body.incidente);
        if (opt.isPresent()) {
        	Incidente incidente = opt.get();
        	Cuenta cuenta = respuesta.getCuenta();
        	if (cuenta == null) {
            	return respuesta.internalError("Error interno al leer cuenta");
        	}
        	
        	System.out.println("incidente: " + incidente.getId());
        	System.out.println("cuenta: " + cuenta.getId());
        	
        	Reaccion reaccion = new Reaccion(incidente, cuenta, body.reaccion);
        	reaccionRepo.save(reaccion);    
        	
        	return get();    	
        } else {
        	return respuesta.requestError("No hay Incidente con id = " + body.incidente);
        }
    }

}
