package ar.edu.undav.colaboreitor;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.undav.colaboreitor.domain.Localidad;
import ar.edu.undav.colaboreitor.repository.LocalidadRepo;

import java.util.List;

@RestController
public class LocalidadController {
	
	@Autowired
	private LocalidadRepo localidadRepo;

    @RequestMapping("/localidad")
    public String index() throws JSONException {
        System.out.println("GET /localidad");
        
        List<Localidad> localidades = localidadRepo.findAll();
        
        JSONObject json = new JSONObject();
        
        json.put("status", "ok");
        
        JSONObject[] localidad = new JSONObject[localidades.size()];
        int i = 0;
        for (Localidad loc : localidades) {
	        JSONObject jsonLoc = new JSONObject();
	        
	        jsonLoc.put("id", loc.getId());
	        jsonLoc.put("nombre", loc.getNombre());
	        jsonLoc.put("lng", loc.getLng());
	        jsonLoc.put("lat", loc.getLat());
	        
	        localidad[i] = jsonLoc;
	        i += 1;
        }
        json.put("localidad", localidad);
        
        return json.toString();
    }

}