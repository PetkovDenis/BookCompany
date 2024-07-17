package ru.petkov.bookcompany.service.book;

import org.springframework.stereotype.Service;
import ru.petkov.bookcompany.entity.Book;
import ru.petkov.bookcompany.repository.book.BookRepository;

import java.util.List;

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
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow();
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

    @Override
    public List<Book> findBooksByClientId(Long clientId) {
        return bookRepository.findBooksByClient_ClientId(clientId);
    }
}
