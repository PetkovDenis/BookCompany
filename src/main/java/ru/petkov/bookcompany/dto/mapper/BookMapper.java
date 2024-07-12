package ru.petkov.bookcompany.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.petkov.bookcompany.dto.BookDTO;
import ru.petkov.bookcompany.entity.Book;

import java.util.List;

@Mapper
@Component
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDTO toBookDTO(Book book);

    List<BookDTO> toBookDTOList(List<Book> books);

    Book toBook(BookDTO bookDTO);
}
