package ar.edu.undav.colaboreitor;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

@RunWith(SpringRunner.class)
@SpringBootTest
public class CuentaTest {

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
				get("/cuenta").session(this.session).accept(MediaType.APPLICATION_JSON_UTF8)
			).andExpect(status().isOk())
    		;
    }

    @Test
    public void testPost() throws Exception {
    	final String requestBody =
    			"{\"username\":\"eric\",\"password\":\"pass\",\"nombre_real\":\"Eric\",\"cp\":\"a1234bcd\",\"dni\":\"88888888\",\"lng\":\"1.0\",\"lat\":\"1.0\"}";
    	
    	this.mockMvc.perform(
				post("/cuenta")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(requestBody)
					.session(this.session)
					.accept(MediaType.APPLICATION_JSON_UTF8)
			).andExpect(status().isOk());
    }
    
    
}
