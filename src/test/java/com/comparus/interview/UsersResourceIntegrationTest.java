package com.comparus.interview;

import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class UsersResourceIntegrationTest extends IntegrationTest {

    @Test
    public void usersList_All() throws Exception {
        mvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Bob"))
                .andExpect(jsonPath("$[0].username").value("bob@domain.com"))
                .andExpect(jsonPath("$[1].name").value("Bohman"))
                .andExpect(jsonPath("$[1].username").value("bohman@domain.com"))
                .andExpect(jsonPath("$[2].name").value("John"))
                .andExpect(jsonPath("$[2].username").value("john@domain.com"));
    }

    @Test
    public void usersList_Search() throws Exception {
        mvc.perform(get("/users?search=oh").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Bohman"))
                .andExpect(jsonPath("$[0].username").value("bohman@domain.com"))
                .andExpect(jsonPath("$[1].name").value("John"))
                .andExpect(jsonPath("$[1].username").value("john@domain.com"));
    }

}
