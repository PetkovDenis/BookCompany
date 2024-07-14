package ru.petkov.bookcompany.controller;

import org.springframework.web.bind.annotation.*;
import ru.petkov.bookcompany.dto.BookDTO;
import ru.petkov.bookcompany.dto.ClientDTO;
import ru.petkov.bookcompany.dto.mapper.BookMapper;
import ru.petkov.bookcompany.dto.mapper.ClientMapper;
import ru.petkov.bookcompany.entity.Book;
import ru.petkov.bookcompany.entity.Client;
import ru.petkov.bookcompany.service.client.ClientService;
import ru.petkov.bookcompany.service.facade.ClientBookFacade;

import java.util.List;


@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;
    private final ClientBookFacade clientBookFacade;
    private final ClientMapper clientMapper;
    private final BookMapper bookMapper;

    public ClientController(ClientService clientService, ClientBookFacade clientBookFacade, ClientMapper clientMapper, BookMapper bookMapper) {
        this.clientService = clientService;
        this.clientBookFacade = clientBookFacade;
        this.clientMapper = clientMapper;
        this.bookMapper = bookMapper;
    }

    @PostMapping("/create")
    public ClientDTO save(@RequestBody ClientDTO clientDTO) {
        Client client = clientMapper.toClient(clientDTO);
        clientService.createClient(client);
        return clientDTO;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        clientService.deleteClient(id);
        return "Client was deleted";
    }

    @GetMapping("/list")
    public List<ClientDTO> getAll() {
        List<Client> clients = clientService.allClients();
        return clientMapper.toClientsDTO(clients);
    }

    @PostMapping("/update")
    public ClientDTO update(@RequestBody ClientDTO clientDTO) {
        Client client = clientMapper.toClient(clientDTO);
        clientService.updateClient(client);
        return clientDTO;
    }

    @PostMapping("take/{bookId}/{clientId}")
    public BookDTO takeBook(@PathVariable Long bookId, @PathVariable Long clientId) {
        Book bookById = clientBookFacade.findById(bookId);
        Book book = clientService.takeBook(clientId, bookById);
        return bookMapper.toBookDTO(book);
    }

    @PostMapping("return/{bookId}/{clientId}")
    public BookDTO returnBook(@PathVariable Long bookId, @PathVariable Long clientId) {
        Book bookById = clientBookFacade.findById(bookId);
        Book book = clientService.returnBook(clientId, bookById);
        return bookMapper.toBookDTO(book);
    }
}
