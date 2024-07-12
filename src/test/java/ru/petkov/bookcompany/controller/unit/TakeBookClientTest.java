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

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TakeBookClientTest {

    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ClientBookFacade clientBookFacade;
    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    void takeBookClient() {

        Client client = new Client();
        client.setClientId(100L);
        client.setFirstName("bob");
        client.setLastName("mob");

        Book book = new Book();
        book.setTitle("title");
        book.setAuthor("author");

        when(clientBookFacade.createBook(book)).thenReturn(book);
        when(clientRepository.findById(client.getClientId())).thenReturn(Optional.of(client));

        clientService.takeBook(client.getClientId(), book);
        clientRepository.save(client);
        verify(clientRepository, times(1)).save(client);

    }
}
