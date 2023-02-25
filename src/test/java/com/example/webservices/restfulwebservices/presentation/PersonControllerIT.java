package com.example.webservices.restfulwebservices.presentation;

import com.example.webservices.restfulwebservices.AbstractTestContainerIT;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest
@Rollback
class PersonControllerIT extends AbstractTestContainerIT {

    @Autowired
    private MockMvc mockMvc;

    @Nested
    @DisplayName("Test method getAllPeople")
    public class TestMethodGetAllPeople {

        @Test
        @DisplayName("Should get all people")
        public void shouldGetAllPeople() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                    .andDo(print())
                    .andExpectAll(jsonPath("$.length()", Matchers.is(5)), status().isOk());
        }

    }

    @Nested
    @DisplayName("Test method getUser")
    public class TestMethodGetPerson {
        @Test
        @DisplayName("Should get the user when user exist")
        public void shouldGetTheUserWhenUserExist() throws Exception {
            String name = "Kerry Shackleton";
            String userId = "3373e18f-c13d-4ffe-b21f-4f7a0825b72b";
            mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", userId))
                    .andDo(print())
                    .andExpectAll(status().isOk(), jsonPath("$.name", Matchers.equalTo(name)));

        }

        @Test
        @DisplayName("Should fail with 404 when user not found")
        public void shouldFailWith404WhenUserNotFound() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", UUID.randomUUID()))
                    .andExpect(status().isNotFound());
        }

    }

}
