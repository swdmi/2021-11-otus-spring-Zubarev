package ru.swdmi.booklibrary.service;

import ru.swdmi.booklibrary.domain.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
}
