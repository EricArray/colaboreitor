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

import org.json.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CpTest {

    @Autowired WebApplicationContext wac; 
    @Autowired MockHttpSession session;
    @Autowired MockHttpServletRequest request;
    
    private MockMvc mockMvc;

    @Before
    public void before() {
    	this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testGet() throws Exception {
    	this.mockMvc.perform(
				get("/cp").session(this.session).accept(MediaType.APPLICATION_JSON_UTF8)
			).andExpect(status().isOk())
    		;
    }

    @Test
    public void testPost() throws Exception {
    	
    	JSONObject json = new JSONObject();
    	json.put("cp", "a1234bcd");
    	json.put("localidad", 2);
    	json.put("lng", "1.0");
    	json.put("lat", "2.0");
    	this.mockMvc.perform(
				post("/cp")
					.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
					.content(json.toString())
					.session(this.session)
					.accept(MediaType.APPLICATION_JSON_UTF8)
			).andExpect(status().isOk());
    }
    
    
}
