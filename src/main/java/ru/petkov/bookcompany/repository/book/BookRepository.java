package ru.petkov.bookcompany.repository.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.petkov.bookcompany.entity.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findBooksByClient_ClientId(Long clientId);
}
