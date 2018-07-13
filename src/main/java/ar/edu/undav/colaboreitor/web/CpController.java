package ar.edu.undav.colaboreitor.web;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
        jsonCp.put("lng", cp.getLng());
        jsonCp.put("lat", cp.getLat());
        
        return jsonCp;
	}

    @RequestMapping(value="/cp", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String get() {
        System.out.println("GET /cp");
        
        try {
            List<Cp> cps = cpRepo.findAll();
            
	        JSONObject[] jsonCps = new JSONObject[cps.size()];
	        int i = 0;
	        for (Cp cp : cps) {
	        	jsonCps[i] = cpJson(cp);
		        i += 1;
	        }
	        
        	return respuesta.ok("cp", jsonCps);
        } catch (JSONException e) {
			// TODO Auto-generated catch bcpk
			e.printStackTrace();
			return respuesta.error("Error interno al leer CP");
		}
    }

    @RequestMapping(value="/cp/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getById(@PathVariable String id) {
        System.out.println("GET /cp/" + id);
        
        Optional<Cp> optCp = cpRepo.findById(id);
        
        if (optCp.isPresent()) {
            try {
            	Cp cp = optCp.get();
            	
    	        JSONObject[] cps = new JSONObject[1];
    		    cps[0] = cpJson(cp);
    	        
            	return respuesta.ok("cp", cps);
            } catch (JSONException e) {
    			// TODO Auto-generated catch bcpk
    			e.printStackTrace();
    			return respuesta.error("Error interno al leer CP");
    		}
        } else {
        	return respuesta.error("No hay CP con id = " + id);
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
    public String post(@RequestBody PostBody body) {
        System.out.println("POST /cp");
        
        Optional<Localidad> optLoc = locRepo.findById(body.localidad);
        if (optLoc.isPresent()) {
        	Localidad loc = optLoc.get();
        	Cp cp = new Cp(body.cp, loc, new BigDecimal(body.lng), new BigDecimal(body.lat));
        	cpRepo.save(cp);    
        	return getById(cp.getCp());    	
        } else {
        	return respuesta.error("No hay localidad con id = " + body.localidad);
        }
    }

    @RequestMapping(value="/cp/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String delete(@PathVariable String id) {
        System.out.println("DELETE /cp/" + id);
        
        Optional<Cp> optCp = cpRepo.findById(id);
        if (optCp.isPresent()) {
            try {
            	Cp cp = optCp.get();
            	
            	cpRepo.delete(cp);
            	
    	        JSONObject[] cps = new JSONObject[1];
    		    cps[0] = cpJson(cp);
    	        
            	return respuesta.ok("cp", cp);
            } catch (JSONException e) {
    			// TODO Auto-generated catch bcpk
    			e.printStackTrace();
    			return respuesta.error("Error interno al leer CP");
    		}
        } else {
        	return respuesta.error("No hay CP con id = " + id);
        }
    }
    
}
