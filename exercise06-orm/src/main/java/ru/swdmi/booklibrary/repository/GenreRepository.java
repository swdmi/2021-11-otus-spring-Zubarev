package ru.swdmi.booklibrary.repository;

import ru.swdmi.booklibrary.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    Optional<Genre> findById(long id);
    List<Genre> findByName(String name);
    List<Genre> findAll();
    Genre save(Genre genre);
}
