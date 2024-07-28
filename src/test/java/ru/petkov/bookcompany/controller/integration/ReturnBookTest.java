package ru.petkov.bookcompany.controller.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
import ru.petkov.bookcompany.service.facade.ClientBookFacade;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ReturnBookTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BookService bookService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientBookFacade clientBookFacade;

    @BeforeEach
    public void setUp() {

        Book book = new Book();
        book.setTitle("title1");
        book.setAuthor("Steve");
        bookService.createBook(book);

        Client client = new Client();
        client.setFirstName("Bob");
        client.setLastName("Smith");
        clientService.createClient(client);

        book.setClient(client);
        bookService.updateBook(book);

    }

    @Test
    public void returnBook() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("http://localhost:8080/client/return/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        BookDTO result = new ObjectMapper().readValue(contentAsString, new TypeReference<>() {
        });

        assertThat(bookService.allBooks().get(0).getAuthor()).isEqualTo(result.getAuthor());
        assertThat(bookService.allBooks().get(0).getTitle()).isEqualTo(result.getTitle());
        assertThat(bookService.findBookById(1L).getClient()).isNull();
        assertThat(bookService.allBooks().size()).isEqualTo(1);

    }

    @AfterEach
    public void clearEntitiesInServices() {
        bookService.deleteBook(1L);
        clientService.deleteClient(1L);
    }
}
