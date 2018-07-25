package ar.edu.undav.colaboreitor;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import ar.edu.undav.colaboreitor.domain.Cp;
import ar.edu.undav.colaboreitor.domain.Localidad;
import ar.edu.undav.colaboreitor.repository.CpRepo;
import ar.edu.undav.colaboreitor.repository.CuentaRepo;
import ar.edu.undav.colaboreitor.repository.IncidenteRepo;
import ar.edu.undav.colaboreitor.repository.LocalidadRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CuentaTest {

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
    	/*
    	 * TODO: test GET /cuenta
    	MockHttpServletRequestBuilder builder = get("/cuenta");
    	builder.session(this.session);
    	builder.accept(MediaType.APPLICATION_JSON_UTF8);
    	builder.header("X-Authorization", "user:pass");
    	
    	ResultActions r = this.mockMvc.perform(builder);
    	r.andExpect(status().isCreated());
    	*/
    }

    @Test
    public void testPost() throws Exception {
        Localidad loc = new Localidad("CpTest_post", new BigDecimal("1.0"), new BigDecimal("1.0"));
        localidadRepo.saveAndFlush(loc);
        
        Cp cp = new Cp("a888aaa", loc, new BigDecimal("1.0"), new BigDecimal("1.0"));
        cpRepo.saveAndFlush(cp);
    	
    	{
	    	final String requestBody = "{" +
	    			"\"username\":\"eric\", \"password\":\"pass\", \"nombre_real\":\"Eric\","+
	    			"\"cp\":\"" + cp.getCp() + "\"," + 
	    			"\"dni\":\"88888888\",\"lng\":\"1.0\",\"lat\":\"1.0\"" +
	    		"}";
	    	
	    	this.mockMvc.perform(
					post("/cuenta")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(requestBody)
						.session(this.session)
						.accept(MediaType.APPLICATION_JSON_UTF8)
				).andExpect(status().isCreated());
    	}
    }
}
