package com.cailam.springmicroservicesdockerpoc.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class ApiControllerTest {

    private ApiController rest;
    private MockMvc endpoint;

    @Before
    public void setup(){
        rest = new ApiController();
        endpoint = standaloneSetup(rest).build();
    }

    @Test
    public void testSuccess() throws Exception {
        endpoint
                .perform(post("/inquiries").contentType(APPLICATION_JSON).content("{\"name\": \"Mickey Mouse\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(hasSize(1)))
                .andExpect(jsonPath("$[0]").value("Mickey Mouse"));

        endpoint
                .perform(post("/inquiries").contentType(APPLICATION_JSON).content("{\"name\": \"John Doe\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(hasSize(2)))
                .andExpect(jsonPath("$[0]").value("Mickey Mouse"))
                .andExpect(jsonPath("$[1]").value("John Doe"));
    }

    @Test
    public void testFailure() throws Exception{
        endpoint
                .perform(post("/inquiries").contentType(APPLICATION_JSON).content("{\"junk\": \"ABC XYZ\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("'name' is required."));

    }
}
