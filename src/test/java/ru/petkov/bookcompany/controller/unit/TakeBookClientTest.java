package ru.petkov.bookcompany.controller.unit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.petkov.bookcompany.entity.Book;
import ru.petkov.bookcompany.entity.Client;
import ru.petkov.bookcompany.service.book.BookServiceImpl;
import ru.petkov.bookcompany.service.client.ClientServiceImpl;
import ru.petkov.bookcompany.service.facade.ClientBookFacade;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TakeBookClientTest {

    @Mock
    private ClientServiceImpl clientService;
    @Mock
    private BookServiceImpl bookService;
    @InjectMocks
    private ClientBookFacade clientBookFacade;

    @Test
    void takeBookClient_shouldTakeBookClient() {

        Client client = new Client();
        client.setClientId(1L);
        client.setFirstName("bob");
        client.setLastName("mob");

        Book book = new Book();
        book.setId(1L);
        book.setTitle("title");
        book.setAuthor("author");

        when(clientService.findClientById(1L)).thenReturn(client);
        when(bookService.findBookById(1L)).thenReturn(book);
        when(clientBookFacade.takeBook(client.getClientId(), book.getId())).thenReturn(book);

        Book result = clientBookFacade.takeBook(client.getClientId(), book.getId());

        assertEquals("title", result.getTitle());
        assertEquals("author", result.getAuthor());

        assertThat(result.getClient()).isEqualTo(client);
    }
}
