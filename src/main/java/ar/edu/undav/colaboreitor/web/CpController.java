package ar.edu.undav.colaboreitor.web;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.undav.colaboreitor.domain.Cp;
import ar.edu.undav.colaboreitor.domain.Localidad;
import ar.edu.undav.colaboreitor.repository.CpRepo;
import ar.edu.undav.colaboreitor.repository.LocalidadRepo;

@RestController
public class CpController {

	@Autowired
	private CpRepo cpRepo;

	@Autowired
	private LocalidadRepo locRepo;
	
	@Autowired
	private Respuesta respuesta;
	
	JSONObject cpJson(Cp cp) throws JSONException {
        JSONObject jsonCp = new JSONObject();
        
        jsonCp.put("cp", cp.getCp());
        jsonCp.put("localidad", cp.getLocalidad().getId());
        jsonCp.put("lng", cp.getLng().toString());
        jsonCp.put("lat", cp.getLat().toString());
        
        return jsonCp;
	}

    @RequestMapping(value="/cp", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> get(HttpServletResponse response) throws JSONException {
        System.out.println("GET /cp");
    
        List<Cp> cps = cpRepo.findAll();
        JSONArray arr = new JSONArray();
        for (Cp cp : cps) {
        	arr.put(cpJson(cp));
        }
        
    	return respuesta.ok("cp", arr);
    }

    @RequestMapping(value="/cp/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getById(@PathVariable String id) throws JSONException {
        System.out.println("GET /cp/" + id);
        
        Optional<Cp> optCp = cpRepo.findById(id);
        
        if (optCp.isPresent()) {
        	Cp cp = optCp.get();

            JSONArray arr = new JSONArray();
            arr.put(cpJson(cp));
	        
		    return respuesta.ok("cp", arr);
        } else {
        	return respuesta.requestError("No hay CP con id = " + id);
        }
    }
    
    public static class PostBody {
    	public String cp;
    	public Long localidad;
    	public String lng;
    	public String lat;
    	
		public PostBody() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Long getLocalidad() {
			return localidad;
		}
		public void setNombre(Long localidad) {
			this.localidad = localidad;
		}
		public String getLng() {
			return lng;
		}
		public void setLng(String lng) {
			this.lng = lng;
		}
		public String getLat() {
			return lat;
		}
		public void setLat(String lat) {
			this.lat = lat;
		}
    }

    @RequestMapping(value="/cp", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> post(@RequestBody PostBody body) throws JSONException {
        System.out.println("POST /cp");
        
        Optional<Localidad> optLoc = locRepo.findById(body.localidad);
        if (optLoc.isPresent()) {
        	Localidad loc = optLoc.get();
        	Cp cp = new Cp(body.cp, loc, new BigDecimal(body.lng), new BigDecimal(body.lat));
        	cpRepo.save(cp);

            JSONArray arr = new JSONArray();
            arr.put(cpJson(cp));
	        
        	return respuesta.conStatus(HttpStatus.CREATED, "cp", arr);
        } else {
        	return respuesta.requestError("No hay localidad con id = " + body.localidad);
        }
    }

    @RequestMapping(value="/cp/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> delete(@PathVariable String id) throws JSONException {
        System.out.println("DELETE /cp/" + id);
        
        Optional<Cp> optCp = cpRepo.findById(id);
        if (optCp.isPresent()) {
        	Cp cp = optCp.get();
        	
        	cpRepo.delete(cp);

            JSONArray arr = new JSONArray();
            arr.put(cpJson(cp));
	        
        	return respuesta.conStatus(HttpStatus.ACCEPTED, "cp", arr);
        } else {
        	return respuesta.requestError("No hay CP con id = " + id);
        }
    }
    
}
