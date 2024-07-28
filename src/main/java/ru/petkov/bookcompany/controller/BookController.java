package ru.petkov.bookcompany.controller;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final RandomIdFeign randomId;
    private final BookMapper bookMapper;


    @GetMapping("/list")
    public List<BookDTO> getAllBooks() {
        return bookMapper.toBookDTOList(bookService.allBooks());
    }

    @PostMapping("/delete/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @PostMapping("/create")
    public BookDTO createBook(@RequestBody BookDTO bookDTO) {
        Book book = bookMapper.toBook(bookDTO);
        book.setRandomId(getRandomId());
        return bookMapper.toBookDTO(bookService.createBook(book));
    }

    @PostMapping("/update")
    public BookDTO updateBook(@RequestBody BookDTO bookDTO) {
        Book book = bookMapper.toBook(bookDTO);
        return bookMapper.toBookDTO(bookService.updateBook(book));
    }

    private UUID getRandomId() {
        String json = randomId.getRandomId();
        JSONObject object = new JSONObject(json);
        String uuid = object.get("uuid").toString();
        return UUID.fromString(uuid);
    }

    @PostMapping("/borrowed/{clientId}")
    public List<BookDTO> getListBorrowedBooks(@PathVariable Long clientId) {
        List<Book> booksByClientId = bookService.findBooksByClientId(clientId);
        return bookMapper.toBookDTOList(booksByClientId);
    }
}
