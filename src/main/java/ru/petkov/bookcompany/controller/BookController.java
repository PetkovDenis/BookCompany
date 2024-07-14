package ru.petkov.bookcompany.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public BookController(BookService bookService, RandomIdFeign randomId) {
        this.bookService = bookService;
        this.randomId = randomId;
    }

    @GetMapping("/list")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return new ResponseEntity<>(BookMapper.INSTANCE.toBookDTOList(bookService.allBooks()), HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
      bookService.deleteBook(id);
      return new ResponseEntity<>("Book was deleted", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        Book book = BookMapper.INSTANCE.toBook(bookDTO);
        book.setRandomId(getRandomId());
        bookService.createBook(book);
        return new ResponseEntity<>(bookDTO, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<BookDTO> updateBook(@RequestBody BookDTO bookDTO) {
        Book book = BookMapper.INSTANCE.toBook(bookDTO);
        bookService.updateBook(book);
        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }

    @GetMapping("/id")
    public UUID getRandomId() {
        String json = randomId.getRandomId();
        JSONObject object = new JSONObject(json);
        String uuid = object.get("uuid").toString();
        return UUID.fromString(uuid);

    }

    @PostMapping("/borrowed/{clientId}")
    public ResponseEntity<List<BookDTO>> getListBorrowedBooks(@PathVariable Long clientId) {
        List<Book> books = bookService.allBooks().stream().filter(book -> book.getClient().getClientId().equals(clientId)).toList();
        return new ResponseEntity<>(BookMapper.INSTANCE.toBookDTOList(books), HttpStatus.OK);
    }
}
