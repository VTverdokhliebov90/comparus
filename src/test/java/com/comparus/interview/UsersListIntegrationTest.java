package com.comparus.interview;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = InterviewApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.config.additional-location=classpath:application-integrationtest.yaml")
public class UsersListIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void usersList() throws Exception {
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
