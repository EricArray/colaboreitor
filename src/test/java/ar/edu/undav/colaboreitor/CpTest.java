package ar.edu.undav.colaboreitor;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import ar.edu.undav.colaboreitor.domain.Cp;
import ar.edu.undav.colaboreitor.domain.Cuenta;
import ar.edu.undav.colaboreitor.domain.Localidad;
import ar.edu.undav.colaboreitor.repository.CpRepo;
import ar.edu.undav.colaboreitor.repository.CuentaRepo;
import ar.edu.undav.colaboreitor.repository.IncidenteRepo;
import ar.edu.undav.colaboreitor.repository.LocalidadRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CpTest {

    @Autowired WebApplicationContext wac; 
    @Autowired MockHttpSession session;
    @Autowired MockHttpServletRequest request;
    
	@Autowired CuentaRepo cuentaRepo;
	@Autowired LocalidadRepo localidadRepo;
	@Autowired CpRepo cpRepo;
	@Autowired IncidenteRepo incidenteRepo;

    
    private MockMvc mockMvc;

    @Before
    public void before() {
    	this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testGet() throws Exception {
    	this.mockMvc.perform(
				get("/cp")
					.session(this.session).accept(MediaType.APPLICATION_JSON_UTF8)
			).andExpect(status().isOk())
    		;
    }

    @Test
    public void testPost() throws Exception {
        Localidad loc = new Localidad("CpTest_post", new BigDecimal("1.0"), new BigDecimal("1.0"));
        localidadRepo.saveAndFlush(loc);
        
    	Cp cp = new Cp("a3333aaa", loc, new BigDecimal("1.0"), new BigDecimal("1.0"));
    	cpRepo.saveAndFlush(cp);

    	Cuenta cuenta = new Cuenta("CpTest_Cuenta", "pass", "CTC", cp, new BigDecimal("1.0"), new BigDecimal("1.0"), 0, Timestamp.valueOf(LocalDateTime.now()));
    	cuentaRepo.saveAndFlush(cuenta);
        SecurityContextHolder.getContext().setAuthentication(cuenta);
        
    	final String requestBody = "{\"cp\":\"a4444aaa\", \"localidad\":" + loc.getId() + ", \"lng\":\"1.0\", \"lat\":\"1.0\"}";
    	this.mockMvc.perform(
				post("/cp")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(requestBody)
					.session(this.session)
					.accept(MediaType.APPLICATION_JSON_UTF8)
			).andExpect(status().isCreated());
    }
    
    
}
