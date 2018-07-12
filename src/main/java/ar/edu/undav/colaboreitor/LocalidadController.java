package ar.edu.undav.colaboreitor;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.undav.colaboreitor.domain.Localidad;
import ar.edu.undav.colaboreitor.repository.LocalidadRepo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
public class LocalidadController {
	
	@Autowired
	private LocalidadRepo localidadRepo;
	
	private Respuesta respuesta = new Respuesta();
	
	JSONObject localidadJson(Localidad loc) throws JSONException {
        JSONObject jsonLoc = new JSONObject();
        
        jsonLoc.put("id", loc.getId());
        jsonLoc.put("nombre", loc.getNombre());
        jsonLoc.put("lng", loc.getLng());
        jsonLoc.put("lat", loc.getLat());
        
        return jsonLoc;
	}

    @RequestMapping(value="/localidad", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String get() {
        System.out.println("GET /localidad");
        
        try {
            List<Localidad> localidades = localidadRepo.findAll();
            
	        JSONObject[] localidad = new JSONObject[localidades.size()];
	        int i = 0;
	        for (Localidad loc : localidades) {
		        localidad[i] = localidadJson(loc);
		        i += 1;
	        }
	        
        	return respuesta.ok("localidad", localidad);
        } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return respuesta.error("Error interno al leer localidades");
		}
    }

    @RequestMapping(value="/localidad/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getById(@PathVariable long id) {
        System.out.println("GET /localidad/" + id);
        
        Optional<Localidad> optLoc = localidadRepo.findById(id);
        
        if (optLoc.isPresent()) {
            try {
            	Localidad loc = optLoc.get();
            	
    	        JSONObject[] localidad = new JSONObject[1];
    		    localidad[0] = localidadJson(loc);
    	        
            	return respuesta.ok("localidad", localidad);
            } catch (JSONException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			return respuesta.error("Error interno al leer localidades");
    		}
        } else {
        	return respuesta.error("No hay localidad con id = " + id);
        }
    }

    @RequestMapping(value="/localidad", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String post(@RequestBody String nombre, @RequestBody BigDecimal lng, @RequestBody BigDecimal lat) {
        System.out.println("POST /localidad");
        
    	Localidad loc = new Localidad(nombre, lng, lat);
    	localidadRepo.save(loc);
    	
    	return getById(loc.getId());
    }

}
