package ru.petkov.bookcompany.service.book;

import org.springframework.stereotype.Service;
import ru.petkov.bookcompany.entity.Book;
import ru.petkov.bookcompany.repository.book.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book createBook(Book book) {
        bookRepository.save(book);
        return book;
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book findBookById(Long id) {
        Optional<Book> byId = bookRepository.findById(id);
        return byId.orElseThrow();
    }

    @Override
    public Book updateBook(Book book) {
        Book bookById = findBookById(book.getId());
        bookById.setTitle(book.getTitle());
        bookById.setAuthor(book.getAuthor());
        bookById.setClient(book.getClient());
        bookRepository.save(bookById);
        return bookById;
    }
}
