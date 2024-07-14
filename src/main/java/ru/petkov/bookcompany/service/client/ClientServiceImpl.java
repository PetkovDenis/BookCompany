package ru.petkov.bookcompany.service.client;


import org.springframework.stereotype.Service;
import ru.petkov.bookcompany.entity.Book;
import ru.petkov.bookcompany.entity.Client;
import ru.petkov.bookcompany.repository.client.ClientRepository;
import ru.petkov.bookcompany.service.facade.ClientBookFacade;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final ClientBookFacade clientBookFacade;

    public ClientServiceImpl(ClientRepository clientRepository, ClientBookFacade clientBookFacade) {
        this.clientRepository = clientRepository;
        this.clientBookFacade = clientBookFacade;
    }

    @Override
    public List<Client> allClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client createClient(Client client) {
        clientRepository.save(client);
        return client;
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Client findClientById(Long id) {
        return clientRepository.findById(id).get();
    }

    @Override
    public Client updateClient(Client client) {
        Client clientById = findClientById(client.getClientId());
        clientById.setFirstName(client.getFirstName());
        clientById.setLastName(client.getLastName());
        return clientById;
    }

    @Override
    public Book takeBook(Long clientId, Book book) {
        Client byId = clientRepository.findById(clientId).orElseThrow();
        book.setClient(byId);
        clientBookFacade.updateBook(book);
        return book;
    }

    @Override
    public Book returnBook(Long clientId, Book book) {
        book.setClient(null);
        return clientBookFacade.updateBook(book);
    }
}
