package ru.petkov.bookcompany.controller.integration;

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
import ru.petkov.bookcompany.entity.Book;
import ru.petkov.bookcompany.entity.Client;
import ru.petkov.bookcompany.service.book.BookService;
import ru.petkov.bookcompany.service.client.ClientService;

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
    public void init() {

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
    public void testBorrowedBooks() throws Exception {
        mockMvc.perform(post("http://localhost:8080/book/borrowed/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("title5"))
                .andExpect(jsonPath("$[0].author").value("Steven"))
                .andReturn();

        assertThat(bookService.findBookById(2L).getTitle()).isEqualTo("title5");
        assertThat(clientService.findClientById(2L).getFirstName()).isEqualTo("Bob");

    }

    @AfterEach
    public void clearEntities() {
        bookService.deleteBook(2L);
        clientService.deleteClient(2L);
    }
}