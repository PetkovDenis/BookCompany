package ru.petkov.bookcompany.controller;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import ru.petkov.bookcompany.dto.BookDTO;
import ru.petkov.bookcompany.dto.mapper.BookMapper;
import ru.petkov.bookcompany.entity.Book;
import ru.petkov.bookcompany.feign.RandomIdFeign;
import ru.petkov.bookcompany.service.book.BookService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
    private final RandomIdFeign randomId;
    private final BookMapper bookMapper;

    public BookController(BookService bookService, RandomIdFeign randomId, BookMapper bookMapper) {
        this.bookService = bookService;
        this.randomId = randomId;
        this.bookMapper = bookMapper;
    }

    @GetMapping("/list")
    public List<BookDTO> getAllBooks() {
        return bookMapper.toBookDTOList(bookService.allBooks());
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "Book was deleted";
    }

    @PostMapping("/create")
    public BookDTO createBook(@RequestBody BookDTO bookDTO) {
        Book book = bookMapper.toBook(bookDTO);
        book.setRandomId(getRandomId());
        bookService.createBook(book);
        return bookDTO;
    }

    @PostMapping("/update")
    public BookDTO updateBook(@RequestBody BookDTO bookDTO) {
        Book book = bookMapper.toBook(bookDTO);
        bookService.updateBook(book);
        return bookDTO;
    }

    public UUID getRandomId() {
        String json = randomId.getRandomId();
        JSONObject object = new JSONObject(json);
        String uuid = object.get("uuid").toString();
        return UUID.fromString(uuid);
    }

    @PostMapping("/borrowed/{clientId}")
    public List<BookDTO> getListBorrowedBooks(@PathVariable Long clientId) {
        List<Book> books = bookService.allBooks().stream().filter(book -> book.getClient().getClientId().equals(clientId)).toList();
        return bookMapper.toBookDTOList(books);
    }
}
