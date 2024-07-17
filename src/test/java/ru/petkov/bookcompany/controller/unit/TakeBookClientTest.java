package ru.petkov.bookcompany.controller.unit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.petkov.bookcompany.entity.Book;
import ru.petkov.bookcompany.entity.Client;
import ru.petkov.bookcompany.repository.client.ClientRepository;
import ru.petkov.bookcompany.service.client.ClientServiceImpl;
import ru.petkov.bookcompany.service.facade.ClientBookFacade;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TakeBookClientTest {

    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ClientBookFacade clientBookFacade;
    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    void testTakeBookClient() {

        Client client = new Client();
        client.setClientId(100L);
        client.setFirstName("bob");
        client.setLastName("mob");

        Book book = new Book();
        book.setTitle("title");
        book.setAuthor("author");

        when(clientBookFacade.takeBook(client, book.getId())).thenReturn(book);
        when(clientRepository.findById(client.getClientId())).thenReturn(Optional.of(client));

        Book book1 = clientService.takeBook(clientRepository.findById(client.getClientId()).get().getClientId(), book.getId());

        assertEquals("title", book1.getTitle());
        assertEquals("author", book1.getAuthor());

    }
}
