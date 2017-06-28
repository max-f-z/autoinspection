package com.autoinspection.polaris.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.autoinspection.polaris.PolarisApplication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PolarisApplication.class)
@SpringBootTest
public class UserControllerTest {

    private MockMvc mockMvc;
    
    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
    	this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testGetUsers() throws Exception {
    	this.mockMvc.perform(get("/users/1"))
        .andExpect(status().isOk());
    }
}
