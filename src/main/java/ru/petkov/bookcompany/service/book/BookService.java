package ru.petkov.bookcompany.service.book;

import org.springframework.stereotype.Service;
import ru.petkov.bookcompany.entity.Book;

import java.util.List;

@Service
public interface BookService {

    List<Book> allBooks();

    Book createBook(Book book);

    void deleteBook(Long id);

    Book updateBook(Book book);

    Book findBookById(Long id);

}
