package com.example.webservices.restfulwebservices.presentation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Locale;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerI18nIT {
    @Autowired
    private MockMvc mockMvc;

    public static Stream<Arguments> i18nHelloWorldProvider() {
        return Stream.of(
                Arguments.arguments(Locale.FRENCH, "Bonjour le monde"),
                Arguments.arguments(Locale.ENGLISH, "Hello world"),
                Arguments.arguments(Locale.CHINESE, "Default Message")
        );
    }

    @ParameterizedTest(name = "[{index}] Test local {0}")
    @MethodSource("i18nHelloWorldProvider")
    @DisplayName("Test i18n hello world")
    public void testI18nHelloWorld(Locale locale, String message) throws Exception {

        String content = mockMvc.perform(MockMvcRequestBuilders.get("/users/i18n").locale(locale))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(content).isEqualTo(message);
    }


}
