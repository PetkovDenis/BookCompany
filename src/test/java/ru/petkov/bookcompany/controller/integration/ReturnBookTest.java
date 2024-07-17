package ru.petkov.bookcompany.controller.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.petkov.bookcompany.entity.Book;
import ru.petkov.bookcompany.entity.Client;
import ru.petkov.bookcompany.service.book.BookService;
import ru.petkov.bookcompany.service.client.ClientService;

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

    @BeforeEach
    public void init() {

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

        clientService.takeBook(client.getClientId(), book.getId());
    }

    @Test
    public void testReturnBook() throws Exception {
        mockMvc.perform(post("http://localhost:8080/client/return/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @AfterEach
    public void clearEntities() {
        bookService.deleteBook(1L);
        clientService.deleteClient(1L);
    }
}
