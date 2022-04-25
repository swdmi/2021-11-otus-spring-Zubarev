package ru.swdmi.booklibrary.repository;

import ru.swdmi.booklibrary.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    Optional<Author> findById(long id);
    List<Author> findByName(String name);
    List<Author> findAll();
    Author save(Author author);
}
