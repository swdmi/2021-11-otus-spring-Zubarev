package ru.swdmi.booklibrary.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import ru.swdmi.booklibrary.dao.mapper.BookMapper;
import ru.swdmi.booklibrary.domain.Book;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final BookMapper bookMapper;

    @Override
    public Book insert(Book book) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource(
                Map.of(
                        "title", book.getTitle(),
                        "authorId", book.getAuthor().getId(),
                        "genreId", book.getGenre().getId()
                ));

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update("insert into books (title, author_id, genre_id) values (:title, :authorId, :genreId);",
                paramSource, keyHolder);
        book.setId(keyHolder.getKeyAs(Long.class));
        return book;
    }

    @Override
    public Optional<Book> findById(long id) {
        String findAllQuery = "select b.id, b.title, a.id as author_id, a.name as author_name, g.id as genre_id, g.name as genre_name" +
                " from books b" +
                " left join authors a on b.author_id = a.id" +
                " left join genres g on b.genre_id = g.id" +
                " where b.id = :id;";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(findAllQuery,
                    Map.of("id", id), bookMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Book update(Book book) {
        jdbcTemplate.update("update books set title = :title, author_id = :authorId, genre_id = :genreId where id = :id",
                Map.of(
                        "title", book.getTitle(),
                        "authorId", book.getAuthor().getId(),
                        "genreId", book.getGenre().getId(),
                        "id", book.getId()
                )
        );
        return book;
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("delete from books where id = :id",
                Map.of("id", id));
    }

    @Override
    public List<Book> findAll() {
        String findAllQuery = "select b.id, b.title, a.id as author_id, a.name as author_name, g.id as genre_id, g.name as genre_name" +
                " from books b" +
                " left join authors a on b.author_id = a.id" +
                " left join genres g on b.genre_id = g.id;";
        return jdbcTemplate.query(findAllQuery, bookMapper);
    }
}
