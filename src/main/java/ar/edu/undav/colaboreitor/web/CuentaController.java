package ar.edu.undav.colaboreitor.web;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

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

import ar.edu.undav.colaboreitor.domain.Cp;
import ar.edu.undav.colaboreitor.domain.Cuenta;
import ar.edu.undav.colaboreitor.repository.CpRepo;
import ar.edu.undav.colaboreitor.repository.CuentaRepo;

@RestController
public class CuentaController {

	@Autowired private CuentaRepo cuentaRepo;
	@Autowired private CpRepo cpRepo;

	@Autowired private Respuesta respuesta;
	
	JSONObject cuentaJson(Cuenta cuenta) throws JSONException {
        JSONObject json = new JSONObject();
        
        json.put("id", cuenta.getId());
        json.put("cp", cuenta.getCp().getCp());
        json.put("lng", cuenta.getLng().toString());
        json.put("lat", cuenta.getLat().toString());
        
        return json;
	}

    @RequestMapping(value="/cuenta", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> get() throws JSONException {
        System.out.println("GET /cuenta");
        
        try {
            Cuenta cuenta = respuesta.getCuenta();
        	return respuesta.ok("cuenta", cuentaJson(cuenta));
        } catch (Exception e) {
        	return respuesta.internalError("Error interno al leer cuenta");
        }
    }
    
    public static class PostBody {
    	public String username;
    	public String password;
    	public String nombre_real;
    	public String cp;
    	public String dni;
    	public String lng;
    	public String lat;
    	
		public PostBody() {
			super();
			// TODO Auto-generated constructor stub
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getCp() {
			return cp;
		}

		public void setCp(String cp) {
			this.cp = cp;
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

    @RequestMapping(value="/cuenta", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> post(@RequestBody PostBody body) throws JSONException {
        System.out.println("POST /cuenta");
        
        Optional<Cp> optCp = cpRepo.findById(body.cp);
        if (optCp.isPresent()) {
        	Cp cp = optCp.get();
        	long dni = Long.parseLong(body.dni); 
        	Cuenta cuenta = new Cuenta(body.username, body.password, body.nombre_real,
        			cp, new BigDecimal(body.lng), new BigDecimal(body.lat), dni,
        			Timestamp.valueOf(LocalDateTime.now())
        			);
        	cuentaRepo.save(cuenta);  

        	return respuesta.conStatus(HttpStatus.CREATED, "cuenta", cuentaJson(cuenta));
        } else {
        	return respuesta.requestError("No hay CP = " + body.cp);
        }
    }
}
