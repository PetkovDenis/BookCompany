package ru.petkov.bookcompany.controller.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.petkov.bookcompany.dto.BookDTO;
import ru.petkov.bookcompany.entity.Book;
import ru.petkov.bookcompany.entity.Client;
import ru.petkov.bookcompany.service.book.BookService;
import ru.petkov.bookcompany.service.client.ClientService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class BorrowedBookTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BookService bookService;
    @Autowired
    private ClientService clientService;

    @BeforeEach
    public void setUp() {

        Book book = new Book();
        book.setTitle("title5");
        book.setAuthor("Steven");
        bookService.createBook(book);

        Client client = new Client();
        client.setFirstName("Bob");
        client.setLastName("Smith");
        clientService.createClient(client);

        book.setClient(client);
        bookService.updateBook(book);
    }

    @Test
    public void booksBorrowedClient_shouldReturnBooksBorrowedClient() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("http://localhost:8080/book/borrowed/" + clientService.allClients().get(0).getClientId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("title5"))
                .andExpect(jsonPath("$[0].author").value("Steven"))
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        List<BookDTO> result = new ObjectMapper().readValue(contentAsString, new TypeReference<>() {});

        assertThat(bookService.allBooks().get(0).getTitle()).isEqualTo(result.get(0).getTitle());
        assertThat(bookService.allBooks().get(0).getAuthor()).isEqualTo(result.get(0).getAuthor());

    }

    @AfterEach
    public void clearEntitiesInServices() {
        bookService.deleteBook(1L);
        clientService.deleteClient(1L);
    }
}