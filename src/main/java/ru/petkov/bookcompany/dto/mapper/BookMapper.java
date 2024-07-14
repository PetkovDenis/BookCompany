package ru.petkov.bookcompany.dto.mapper;

import org.mapstruct.Mapper;
import ru.petkov.bookcompany.dto.BookDTO;
import ru.petkov.bookcompany.entity.Book;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDTO toBookDTO(Book book);

    List<BookDTO> toBookDTOList(List<Book> books);

    Book toBook(BookDTO bookDTO);
}
