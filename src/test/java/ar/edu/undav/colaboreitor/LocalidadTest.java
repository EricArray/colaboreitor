package ar.edu.undav.colaboreitor;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocalidadTest {

    @Autowired WebApplicationContext wac; 
    @Autowired MockHttpSession session;
    @Autowired MockHttpServletRequest request;
    
    private MockMvc mockMvc;

    @Before
    public void before() {
    	this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getLocalidad() throws Exception {
    	this.mockMvc.perform(
				get("/localidad").session(this.session).accept(MediaType.APPLICATION_JSON_UTF8)
			).andExpect(status().isOk())
    		;
    }

    @Test
    public void postLocalidad() throws Exception {
    	this.session.putValue("nombre", "Lanús");
    	this.session.putValue("lng", "1.0");
    	this.session.putValue("lat", "2.0");
    	this.mockMvc.perform(
				post("/localidad").session(this.session).accept(MediaType.APPLICATION_JSON_UTF8)
			).andExpect(status().isOk())
    		;
    }
    
    
}
