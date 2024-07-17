package ru.petkov.bookcompany.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.petkov.bookcompany.dto.BookDTO;
import ru.petkov.bookcompany.dto.ClientDTO;
import ru.petkov.bookcompany.dto.mapper.BookMapper;
import ru.petkov.bookcompany.dto.mapper.ClientMapper;
import ru.petkov.bookcompany.entity.Client;
import ru.petkov.bookcompany.service.client.ClientService;

import java.util.List;


@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;
    private final BookMapper bookMapper;

    @PostMapping("/create")
    public ClientDTO save(@RequestBody ClientDTO clientDTO) {
        Client client = clientService.createClient(clientMapper.toClient(clientDTO));
        return clientMapper.toClientDTO(client);
    }

    @PostMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        clientService.deleteClient(id);
    }

    @GetMapping("/list")
    public List<ClientDTO> getAll() {
        List<Client> clients = clientService.allClients();
        return clientMapper.toClientsDTO(clients);
    }

    @PostMapping("/update")
    public ClientDTO update(@RequestBody ClientDTO clientDTO) {
        Client client = clientMapper.toClient(clientDTO);
        return clientMapper.toClientDTO(clientService.updateClient(client));
    }

    @PostMapping("take/{bookId}/{clientId}")
    public BookDTO takeBook(@PathVariable Long bookId, @PathVariable Long clientId) {
        return bookMapper.toBookDTO(clientService.takeBook(clientId, bookId));
    }

    @PostMapping("return/{bookId}")
    public BookDTO returnBook(@PathVariable Long bookId) {
        return bookMapper.toBookDTO(clientService.returnBook(bookId));
    }
}
