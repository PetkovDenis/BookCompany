package ru.petkov.bookcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.petkov.bookcompany.dto.BookDTO;
import ru.petkov.bookcompany.dto.ClientDTO;
import ru.petkov.bookcompany.dto.mapper.BookMapper;
import ru.petkov.bookcompany.dto.mapper.ClientMapper;
import ru.petkov.bookcompany.entity.Book;
import ru.petkov.bookcompany.entity.Client;
import ru.petkov.bookcompany.service.book.BookService;
import ru.petkov.bookcompany.service.client.ClientService;
import ru.petkov.bookcompany.service.facade.ClientBookFacade;

import java.util.List;


@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;
    private final ClientBookFacade clientBookFacade;

    public ClientController(ClientService clientService, ClientBookFacade clientBookFacade) {
        this.clientService = clientService;
        this.clientBookFacade = clientBookFacade;
    }

    @PostMapping("/create")
    public ResponseEntity<ClientDTO> save(@RequestBody ClientDTO clientDTO) {
        Client client = ClientMapper.INSTANCE.toClient(clientDTO);
        clientService.createClient(client);
        return new ResponseEntity<>(clientDTO, HttpStatus.CREATED);
    }
    @PostMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>("Client was deleted", HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ClientDTO>> getAll() {
        List<Client> clients = clientService.allClients();
        return new ResponseEntity<>(ClientMapper.INSTANCE.toClientsDTO(clients), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<ClientDTO> update(@RequestBody ClientDTO clientDTO) {
        Client client = ClientMapper.INSTANCE.toClient(clientDTO);
        clientService.updateClient(client);
        return new ResponseEntity<>(clientDTO, HttpStatus.OK);
    }

    @PostMapping("take/{bookId}/{clientId}")
    public ResponseEntity<BookDTO> takeBook(@PathVariable Long bookId, @PathVariable Long clientId) {
        Book bookById = clientBookFacade.findById(bookId);
        Book book = clientService.takeBook(clientId, bookById);
        BookDTO bookDTO = BookMapper.INSTANCE.toBookDTO(book);
        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }

    @PostMapping("return/{bookId}/{clientId}")
    public ResponseEntity<BookDTO> returnBook(@PathVariable Long bookId, @PathVariable Long clientId) {
        Book bookById = clientBookFacade.findById(bookId);
        Book book = clientService.returnBook(clientId, bookById);
        BookDTO bookDTO = BookMapper.INSTANCE.toBookDTO(book);
        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }
}
