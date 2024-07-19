package ru.petkov.bookcompany.service.facade;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.petkov.bookcompany.entity.Book;
import ru.petkov.bookcompany.entity.Client;
import ru.petkov.bookcompany.service.book.BookService;
import ru.petkov.bookcompany.service.client.ClientService;

@Service
@AllArgsConstructor
public class ClientBookFacade {

    private final BookService bookService;
    private final ClientService clientService;

    public Book takeBook(Long clintId, Long bookId) {
        Client clientById = clientService.findClientById(clintId);
        Book bookById = bookService.findBookById(bookId);
        bookById.setClient(clientById);
        return bookService.updateBook(bookById);
    }

    public Book returnBook(Long bookId) {
        Book bookById = bookService.findBookById(bookId);
        bookById.setClient(null);
        return bookService.updateBook(bookById);
    }
}

