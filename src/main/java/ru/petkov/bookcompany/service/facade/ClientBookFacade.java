package ru.petkov.bookcompany.service.facade;

import org.springframework.stereotype.Service;
import ru.petkov.bookcompany.entity.Book;
import ru.petkov.bookcompany.entity.Client;
import ru.petkov.bookcompany.service.book.BookService;

@Service
public class ClientBookFacade {

    private final BookService bookService;

    public ClientBookFacade(BookService bookService) {
        this.bookService = bookService;
    }

    public Book takeBook(Client client, Long bookId) {
        Book bookById = bookService.findBookById(bookId);
        bookById.setClient(client);
        return bookService.updateBook(bookById);
    }

    public Book returnBook(Long bookId) {
        Book bookById = bookService.findBookById(bookId);
        bookById.setClient(null);
        return bookService.updateBook(bookById);

    }
}

