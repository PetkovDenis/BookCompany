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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TakeBookClientTest {

    private final String title = "title";
    private final String author = "author";
    @Mock
    private ClientServiceImpl clientService;
    @Mock
    private BookServiceImpl bookService;
    @InjectMocks
    private ClientBookFacade clientBookFacade;

    @Test
    void takeBookClient() {

        //Arrange
        Client client = mock(Client.class);
        when(client.getClientId()).thenReturn(1L);

        Book book = mock(Book.class);
        when(book.getId()).thenReturn(1L);
        when(book.getTitle()).thenReturn(title);
        when(book.getAuthor()).thenReturn(author);

        when(clientService.findClientById(1L)).thenReturn(client);
        when(bookService.findBookById(1L)).thenReturn(book);
        when(clientBookFacade.takeBook(client.getClientId(), book.getId())).thenReturn(book);

        // Act
        Book result = clientBookFacade.takeBook(client.getClientId(), book.getId());

        // Assert
        assertThat(result).isEqualTo(book);

        verify(bookService, times(2)).findBookById(1L);
        verify(clientService, times(2)).findClientById(1L);

        assertEquals(title, result.getTitle());
        assertEquals(author, result.getAuthor());

    }
}
