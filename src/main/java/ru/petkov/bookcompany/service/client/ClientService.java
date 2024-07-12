package ru.petkov.bookcompany.service.client;

import ru.petkov.bookcompany.entity.Book;
import ru.petkov.bookcompany.entity.Client;

import java.util.List;

public interface ClientService {

    List<Client> allClients();

    Client createClient(Client client);

    void deleteClient(Long id);

    Client updateClient(Client client);

    Client findClientById(Long id);

    Book takeBook(Long clientId, Book book);

    Book returnBook(Long clientId, Book book);

}
