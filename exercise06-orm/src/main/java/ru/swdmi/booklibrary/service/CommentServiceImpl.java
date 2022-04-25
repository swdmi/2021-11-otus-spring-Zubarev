package ru.swdmi.booklibrary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.swdmi.booklibrary.domain.Book;
import ru.swdmi.booklibrary.domain.Comment;
import ru.swdmi.booklibrary.exception.LibraryException;
import ru.swdmi.booklibrary.repository.BookRepository;
import ru.swdmi.booklibrary.repository.CommentRepository;
import ru.swdmi.booklibrary.service.model.CommentSaveRequest;
import ru.swdmi.booklibrary.service.model.CommentWithIdSaveRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    @Override
    @Transactional
    public Comment save(CommentWithIdSaveRequest commentWithIdSaveRequest) {
        Book book = bookRepository.getOne(commentWithIdSaveRequest.getBookId()).orElseThrow(() -> new LibraryException("Can't find book by id = %s", commentWithIdSaveRequest.getBookId()));
        Comment comment = new Comment(commentWithIdSaveRequest.getId(), commentWithIdSaveRequest.getText(), book);
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public Comment save(CommentSaveRequest commentSaveRequest) {
        if (commentSaveRequest.isCreatable()) {
            Book book = bookRepository.findByName(commentSaveRequest.getBookTitle()).stream()
                    .findFirst()
                    .orElseThrow(() -> new LibraryException("Can not find book with title = %s", commentSaveRequest.getBookTitle()));
            Comment comment = new Comment(commentSaveRequest.getId(), commentSaveRequest.getText(), book);
            return commentRepository.save(comment);
        } else {
            Comment commentInDb = commentRepository.findById(commentSaveRequest.getId())
                    .orElseThrow(() -> new LibraryException("Can't find comment by id = %s", commentSaveRequest.getId()));
            commentInDb.setText(commentSaveRequest.getText());
            return commentRepository.save(commentInDb);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Comment findById(long id) {
        return commentRepository.findById(id).orElseThrow(() -> new LibraryException("Can't find comment by id = %s", id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findByBookId(long bookId) {
        return commentRepository.findByBookId(bookId);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        commentRepository.delete(id);
    }
}
