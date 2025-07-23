package com.simon.hello_service.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloController.class)
class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void echoEndpoint_ShouldReturnEchoedText() throws Exception {
        mvc.perform(post("/echo")
                .contentType("text/plain")
                .content("HelloTest"))
            .andExpect(status().isOk())
            .andExpect(content().string("Echo: HelloTest"));
    }
}