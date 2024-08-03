package com.comparus.interview;


import com.comparus.interview.containers.MySQLContainer;
import com.comparus.interview.containers.PostgresqlContainer;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.JdbcDatabaseContainer;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = InterviewApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.config.additional-location=classpath:application-integrationtest.yaml")
public class IntegrationTest {
    @Autowired
    MockMvc mvc;

    @ClassRule
    public static JdbcDatabaseContainer<PostgresqlContainer> postgresqlContainer = PostgresqlContainer.getInstance();
    @ClassRule
    public static JdbcDatabaseContainer<MySQLContainer> mySQLContainer = MySQLContainer.getInstance();
}
