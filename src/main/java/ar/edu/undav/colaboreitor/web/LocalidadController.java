package ar.edu.undav.colaboreitor.web;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<String> get() throws JSONException {
        System.out.println("GET /localidad");
        
        List<Localidad> localidades = localidadRepo.findAll();
        
        JSONArray arr = new JSONArray();
        for (Localidad loc : localidades) {
        	arr.put(localidadJson(loc));
        }
        
    	return respuesta.ok("localidad", arr);
    }

    @RequestMapping(value="/localidad/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getById(@PathVariable long id) throws JSONException {
        System.out.println("GET /localidad/" + id);
        
        Optional<Localidad> optLoc = localidadRepo.findById(id);
        
        if (optLoc.isPresent()) {
        	Localidad loc = optLoc.get();
        	
	        JSONArray arr = new JSONArray();
	        arr.put(localidadJson(loc));
	        
        	return respuesta.ok("localidad", arr);
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
    public ResponseEntity<String> post(@RequestBody PostBody body) throws JSONException {
        System.out.println("POST /localidad");
        
    	Localidad loc = new Localidad(body.nombre, new BigDecimal(body.lng), new BigDecimal(body.lat));
    	localidadRepo.save(loc);

        JSONArray arr = new JSONArray();
        arr.put(localidadJson(loc));
        
    	return respuesta.conStatus(HttpStatus.CREATED, "localidad", arr);
    }
    
    @RequestMapping(value="/localidad/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> delete(@PathVariable long id) throws JSONException {
        System.out.println("DELETE /localidad/" + id);
        
        Optional<Localidad> optLoc = localidadRepo.findById(id);
        
        if (optLoc.isPresent()) {
        	Localidad loc = optLoc.get();
        	
        	localidadRepo.delete(loc);

	        JSONArray arr = new JSONArray();
	        arr.put(localidadJson(loc));
	        
        	return respuesta.conStatus(HttpStatus.ACCEPTED, "localidad", arr);
        } else {
        	return respuesta.requestError("No hay localidad con id = " + id);
        }
    }
    
}
