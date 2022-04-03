package ru.swdmi.booklibrary.dao;

import ru.swdmi.booklibrary.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    Optional<Author> findById(long id);
    List<Author> findByName(String name);
    List<Author> findAll();
    Author insert(Author author);
}
