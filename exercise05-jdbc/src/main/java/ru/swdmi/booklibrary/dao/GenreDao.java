package ru.swdmi.booklibrary.dao;

import ru.swdmi.booklibrary.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {
    Optional<Genre> findById(long id);
    List<Genre> findByName(String name);
    List<Genre> findAll();
    Genre insert(Genre genre);
}
