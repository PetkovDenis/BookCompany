package ru.petkov.bookcompany.controller.unit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.petkov.bookcompany.entity.Book;
import ru.petkov.bookcompany.repository.book.BookRepository;
import ru.petkov.bookcompany.service.book.BookServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CreateBookTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void createBook() {

        Book book = new Book();
        book.setAuthor("Steven");
        book.setTitle("it");

        when(bookRepository.save(book)).thenReturn(book);

        Book savedBook = bookService.createBook(book);

        assertThat(savedBook).isNotNull();
        assertThat(savedBook.getTitle()).isEqualTo("it");
        assertThat(savedBook.getAuthor()).isEqualTo("Steven");
    }
}
