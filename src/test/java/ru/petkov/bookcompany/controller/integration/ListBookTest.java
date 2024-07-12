package ru.petkov.bookcompany.controller.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class ListBookTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testListBook() throws Exception {
        MvcResult result = mockMvc.perform(post("http://localhost:8080/book/borrowed/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("qdq"))
                .andExpect(jsonPath("$[0].author").value("Ladwwssswt"))
                .andExpect(jsonPath("$[1].title").value("hock"))
                .andExpect(jsonPath("$[1].author").value("Steven"))
                .andReturn();

        assertThat(result).isNotNull();

    }
}