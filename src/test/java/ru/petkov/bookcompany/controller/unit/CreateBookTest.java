package ru.petkov.bookcompany.controller.unit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.petkov.bookcompany.entity.Book;
import ru.petkov.bookcompany.repository.book.BookRepository;
import ru.petkov.bookcompany.service.book.BookServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateBookTest {

    private final String title = "it";
    private final String author = "Steven";
    @InjectMocks
    private BookServiceImpl bookService;
    @Mock
    private BookRepository bookRepository;

    @Test
    public void createBook() {
        // Arrange
        Book book = mock(Book.class);
        when(book.getTitle()).thenReturn(title);
        when(book.getAuthor()).thenReturn(author);

        when(bookRepository.save(book)).thenReturn(book);

        // Act
        Book savedBook = bookService.createBook(book);

        // Assert
        assertThat(savedBook).isEqualTo(book);

        ArgumentCaptor<Book> argumentCaptor = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(argumentCaptor.capture());

        Book result = argumentCaptor.getValue();

        assertThat(result.getTitle()).isEqualTo(title);
        assertThat(result.getAuthor()).isEqualTo(author);

    }
}
