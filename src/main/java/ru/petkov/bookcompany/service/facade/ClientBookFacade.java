package ru.petkov.bookcompany.service.facade;

import org.springframework.stereotype.Service;
import ru.petkov.bookcompany.entity.Book;
import ru.petkov.bookcompany.service.book.BookService;

@Service
public class ClientBookFacade {

    private final BookService bookService;

    public ClientBookFacade(BookService bookService) {
        this.bookService = bookService;
    }

    public Book findById(Long id) {
        return bookService.findBookById(id);
    }

    public Book updateBook(Book book) {
        return bookService.updateBook(book);
    }
}

