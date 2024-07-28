package ru.petkov.bookcompany.service.client;

import org.springframework.stereotype.Service;
import ru.petkov.bookcompany.entity.Client;
import ru.petkov.bookcompany.repository.client.ClientRepository;

import java.util.List;

@Service

public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> allClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
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
}
