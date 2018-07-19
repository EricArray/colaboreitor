package ar.edu.undav.colaboreitor;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.json.JSONObject;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import ar.edu.undav.colaboreitor.repository.IncidenteRepo;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ReaccionTest {

    @Autowired WebApplicationContext wac; 
    @Autowired MockHttpSession session;
    @Autowired MockHttpServletRequest request;
    
    private MockMvc mockMvc;
	@Autowired IncidenteRepo incidenteRepo;

    @Before
    public void before() {
    	this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testGet() throws Exception {
    	/*
    	 * TODO: test GET /reaccion
    	this.mockMvc.perform(
				get("/reaccion")
					.session(this.session).accept(MediaType.APPLICATION_JSON_UTF8)
			).andExpect(status().isOk());
    	 */
    }

    @Test
    public void testPost() throws Exception {
    	String cp;
    	long incidenteId;
    	
    	{
	    	final String requestBody = "{\"cp\":\"a2222aaa\",\"localidad\":1,\"lng\":\"1.0\",\"lat\":\"1.0\"}";
	    	
	    	MvcResult ret = this.mockMvc.perform(
					post("/cp")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(requestBody)
						.session(this.session)
						.accept(MediaType.APPLICATION_JSON_UTF8)
				).andExpect(status().isCreated())
	    			.andReturn();

	    	String response = ret.getResponse().getContentAsString();
	    	JSONObject json = new JSONObject(response);
	    	cp = json.getJSONArray("cp")
	    			.getJSONObject(0)
	    			.getString("cp");
    	}
    	
    	{
	    	final String requestBody = "{" +
	    			"\"cp\":\"" + cp + "\"," +
	    			"\"localidad\":1," +
	    			"\"lng\":\"1.0\",\"lat\":\"1.0\"," +
	    			"\"fotos\":[\"https://imgur.com/iBRE7Dj\"]" +
	    			"}";

	    	//this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	    	
	    	MvcResult ret = this.mockMvc.perform(
					post("/incidente")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(requestBody)
						.session(this.session)
						.accept(MediaType.APPLICATION_JSON_UTF8)
				).andExpect(status().isCreated()).andReturn();
	    	
	    	JSONObject json = new JSONObject(ret.getResponse().getContentAsString());
	    	
	    	incidenteId = json.getJSONArray("incidente").getJSONObject(0).getLong("id");
    	}
    	
    	{
	    	final String requestBody = "{" +
	    			"\"incidente\":" + incidenteId + "," +
	    			"\"reaccion\":1" +
	    			"}";

	    	this.mockMvc.perform(
					post("/reaccion")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(requestBody)
						.session(this.session)
						.accept(MediaType.APPLICATION_JSON_UTF8)
				)//.andExpect(status().isCreated());
	    	.andDo(print());
	    	//throw new Exception(Integer.toString(ret.getResponse().getStatus()) + " -> " + ret.getResponse().getContentAsString());
    	}
    }
    
    
}
