package ru.petkov.bookcompany.service.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.petkov.bookcompany.entity.Book;
import ru.petkov.bookcompany.service.book.BookService;

@Service
public class ClientBookFacade {
    @Autowired
    private BookService bookService;

    public Book createBook(Book book) {
        return bookService.createBook(book);
    }

    public Book findById(Long id){
    return bookService.findBookById(id);
    }
}
