package ru.swdmi.booklibrary.service;

import ru.swdmi.booklibrary.domain.Comment;
import ru.swdmi.booklibrary.service.model.CommentSaveRequest;
import ru.swdmi.booklibrary.service.model.CommentWithIdSaveRequest;

public interface CommentService {
    Comment save(CommentWithIdSaveRequest commentWithIdSaveRequest);
    Comment save(CommentSaveRequest commentSaveRequest);
    Comment findById(long id);
    void deleteById(long id);

}
