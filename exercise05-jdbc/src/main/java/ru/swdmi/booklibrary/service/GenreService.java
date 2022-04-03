package ru.swdmi.booklibrary.service;

import ru.swdmi.booklibrary.domain.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();
}
