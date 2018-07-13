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

import ar.edu.undav.colaboreitor.domain.Localidad;
import ar.edu.undav.colaboreitor.repository.LocalidadRepo;

@RestController
public class LocalidadController {
	
	@Autowired
	private LocalidadRepo localidadRepo;
	
	@Autowired
	private Respuesta respuesta;
	
	JSONObject localidadJson(Localidad loc) throws JSONException {
        JSONObject jsonLoc = new JSONObject();
        
        jsonLoc.put("id", loc.getId());
        jsonLoc.put("nombre", loc.getNombre());
        jsonLoc.put("lng", loc.getLng().toString());
        jsonLoc.put("lat", loc.getLat().toString());
        
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
			return respuesta.internalError("Error interno al leer localidades");
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
    			return respuesta.internalError("Error interno al leer localidades");
    		}
        } else {
        	return respuesta.requestError("No hay localidad con id = " + id);
        }
    }
    
    public static class PostBody {
    	public String nombre;
    	public String lng;
    	public String lat;
    	
		public PostBody() {
			super();
			// TODO Auto-generated constructor stub
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
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

    @RequestMapping(value="/localidad", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String post(@RequestBody PostBody body) {
        System.out.println("POST /localidad");
        
    	Localidad loc = new Localidad(body.nombre, new BigDecimal(body.lng), new BigDecimal(body.lat));
    	localidadRepo.save(loc);
    	
    	return getById(loc.getId());
    }

    @RequestMapping(value="/localidad/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String delete(@PathVariable long id) {
        System.out.println("DELETE /localidad/" + id);
        
        Optional<Localidad> optLoc = localidadRepo.findById(id);
        
        if (optLoc.isPresent()) {
            try {
            	Localidad loc = optLoc.get();
            	
            	localidadRepo.delete(loc);
            	
    	        JSONObject[] localidad = new JSONObject[1];
    		    localidad[0] = localidadJson(loc);
    	        
            	return respuesta.ok("localidad", localidad);
            } catch (JSONException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			return respuesta.internalError("Error interno al leer localidades");
    		}
        } else {
        	return respuesta.requestError("No hay localidad con id = " + id);
        }
    }
    
}
