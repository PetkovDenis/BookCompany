package ru.petkov.bookcompany.repository.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.petkov.bookcompany.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
