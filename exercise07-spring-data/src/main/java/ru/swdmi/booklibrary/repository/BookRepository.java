package ru.swdmi.booklibrary.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.swdmi.booklibrary.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    @EntityGraph("authors-genres-entity-graph")
    Optional<Book> findById(Long id);
    @EntityGraph("authors-genres-entity-graph")
    List<Book> findAll();
    List<Book> findByTitle(String bookTitle);

}
