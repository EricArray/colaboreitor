package ar.edu.undav.colaboreitor.web;

import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.undav.colaboreitor.domain.Cuenta;
import ar.edu.undav.colaboreitor.domain.Incidente;
import ar.edu.undav.colaboreitor.domain.Reaccion;
import ar.edu.undav.colaboreitor.repository.IncidenteRepo;
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
    public ResponseEntity<String> get() throws Exception, JSONException {
        System.out.println("GET /reaccion");
        
    	Cuenta cuenta = respuesta.getCuenta();
    	
        List<Reaccion> reacciones = reaccionRepo.findByCuenta(cuenta);
        
        JSONArray arr = new JSONArray();
        for (Reaccion reaccion : reacciones) {
        	arr.put(reaccionJson(reaccion));
        }
        
    	return respuesta.ok("reaccion", arr);
    }

    public static class PostBody {
    	public Long incidente;
    	public Integer reaccion;
    	
		public PostBody() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Long getIncidente() {
			return incidente;
		}

		public void setIncidente(Long incidente) {
			this.incidente = incidente;
		}

		public Integer getReaccion() {
			return reaccion;
		}

		public void setReaccion(Integer reaccion) {
			this.reaccion = reaccion;
		}
		
    }

    @RequestMapping(value="/reaccion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> post(@RequestBody PostBody body) throws Exception, JSONException {
        System.out.println("POST /reaccion");
        
        Optional<Incidente> opt = incidenteRepo.findById(body.incidente);
        if (opt.isPresent()) {
        	Incidente incidente = opt.get();
        	Cuenta cuenta = respuesta.getCuenta();
        	
        	Reaccion reaccion = new Reaccion(incidente, cuenta, body.reaccion);
        	reaccionRepo.saveAndFlush(reaccion);    

	        JSONArray arr = new JSONArray();
	        arr.put(reaccionJson(reaccion));

	        return respuesta.conStatus(HttpStatus.CREATED, "reaccion", arr);
        } else {
        	return respuesta.requestError("No hay Incidente con id = " + body.incidente);
        }
    }
}
