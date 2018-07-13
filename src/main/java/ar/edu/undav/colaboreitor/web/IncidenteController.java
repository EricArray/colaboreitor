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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.undav.colaboreitor.domain.Cp;
import ar.edu.undav.colaboreitor.domain.Cuenta;
import ar.edu.undav.colaboreitor.domain.Foto;
import ar.edu.undav.colaboreitor.domain.Incidente;
import ar.edu.undav.colaboreitor.domain.Localidad;
import ar.edu.undav.colaboreitor.repository.CpRepo;
import ar.edu.undav.colaboreitor.repository.FotoRepo;
import ar.edu.undav.colaboreitor.repository.IncidenteRepo;
import ar.edu.undav.colaboreitor.repository.LocalidadRepo;

@RestController
public class IncidenteController {

	@Autowired
	private IncidenteRepo incidenteRepo;

	@Autowired
	private CpRepo cpRepo;

	@Autowired
	private FotoRepo fotoRepo;

	@Autowired
	private Respuesta respuesta;
	
	JSONObject incidenteJson(Incidente incidente) throws JSONException {
        JSONObject json = new JSONObject();

        json.put("id", incidente.getId());
        json.put("cp", incidente.getCp());
        json.put("lng", incidente.getLng());
        json.put("lat", incidente.getLat());
        json.put("creacion", incidente.getCreacion());
        json.put("puntos", incidente.getPuntos());
        
        String[] pathFotos = new String[incidente.getFotos().size()];
        int i = 0;
        for (Foto foto : incidente.getFotos()) {
        	pathFotos[i] = foto.getPath();
        	i ++;
        }
        json.put("fotos", pathFotos);
        
        return json;
	}

    @RequestMapping(value="/incidente", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String get(
    		@RequestParam(value = "pos", required = false) String pos,
    		@RequestParam(value = "nombre", required = false) String nombre,
    		@RequestParam(value = "localidad", required = false) Long localidad
    		) {
        System.out.println("GET /incidente");
        
        try {
        	List<Incidente> incidentes;
        	
        	if (pos != null) {
        		int comma = pos.indexOf(',');
        		String x = pos.substring(0, comma);
        		String y = pos.substring(comma + 1);
        		
        		BigDecimal lng = new BigDecimal(x);
        		BigDecimal lat = new BigDecimal(y);
        		
        		incidentes = incidenteRepo.findNear(lng, lat, 2000);
        	} else if (nombre != null) {
        		incidentes = incidenteRepo.findLikeNombre(nombre);
        	} else if (localidad != null) {
        		incidentes = incidenteRepo.findByLocalidad(localidad);
        	} else {
        		incidentes = incidenteRepo.findAll();
        	}
        	
	        JSONObject[] json = new JSONObject[incidentes.size()];
	        int i = 0;
	        for (Incidente incidente : incidentes) {
	        	json[i] = incidenteJson(incidente);
		        i += 1;
	        }
	        
        	return respuesta.ok("incidente", json);
        } catch (JSONException e) {
			// TODO Auto-generated catch bcpk
			e.printStackTrace();
			return respuesta.requestError("Error interno al leer Incidente");
		}
    }

    @RequestMapping(value="/incidente/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getById(@PathVariable Long id) {
        System.out.println("GET /incidente/" + id);
        
        Optional<Incidente> opt = incidenteRepo.findById(id);
        
        if (opt.isPresent()) {
            try {
            	Incidente it = opt.get();
            	
    	        JSONObject[] json = new JSONObject[1];
    	        json[0] = incidenteJson(it);
    	        
            	return respuesta.ok("incidente", json);
            } catch (JSONException e) {
    			// TODO Auto-generated catch bcpk
    			e.printStackTrace();
    			return respuesta.requestError("Error interno al leer Incidente");
    		}
        } else {
        	return respuesta.requestError("No hay Incidente con id = " + id);
        }
    }
    
    public static class PostBody {
    	public String cp;
    	public String nombre;
    	public String lng;
    	public String lat;
    	public String[] fotos;
    	
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

		public String getCp() {
			return cp;
		}
		public void setCp(String cp) {
			this.cp = cp;
		}
		public String[] getFotos() {
			return fotos;
		}
		public void setFotos(String[] fotos) {
			this.fotos = fotos;
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

    @RequestMapping(value="/incidente", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String post(@RequestBody PostBody body) {
        System.out.println("POST /incidente");
        
        Optional<Cp> opt = cpRepo.findById(body.cp);
        if (opt.isPresent()) {
        	Cp cp = opt.get();
        	
        	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        	Cuenta cuenta = (Cuenta) auth.getDetails();
        	
        	Incidente incidente = new Incidente(
        			cuenta, cp, body.nombre,
        			new BigDecimal(body.lng), new BigDecimal(body.lat),
        			Timestamp.valueOf(LocalDateTime.now()));
        	incidenteRepo.save(incidente);    
        	
        	for (String pathFoto : body.fotos) {
        		Foto foto = new Foto(incidente, pathFoto);
        		fotoRepo.save(foto);
        	}
        	
        	return getById(incidente.getId());    	
        } else {
        	return respuesta.requestError("No hay CP = " + body.cp);
        }
    }

}
