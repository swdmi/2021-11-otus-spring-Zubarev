package ru.swdmi.booklibrary.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.swdmi.booklibrary.domain.Comment;
import ru.swdmi.booklibrary.service.CommentService;
import ru.swdmi.booklibrary.service.model.CommentSaveRequest;
import ru.swdmi.booklibrary.service.model.CommentWithIdSaveRequest;
import ru.swdmi.booklibrary.shell.dto.CommentDTO;
import ru.swdmi.booklibrary.shell.dto.mapper.CommentMapper;

import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class CommentCommands {
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    @ShellMethod(value = "Create comment: book id, comment text", key = {"ccid", "createCommentWithId"})
    public String createCommentWithBookId(@ShellOption String bookId, @ShellOption String commentText) {
        Comment comment = commentService.save(new CommentWithIdSaveRequest(commentText, Long.parseLong(bookId)));
        return String.format("Comment successfully created: %s", commentMapper.toDTO(comment));
    }
    @ShellMethod(value = "Create comment: book name, comment text", key = {"cc", "createComment"})
    public String createComment(@ShellOption String bookName, @ShellOption String commentText) {
        Comment comment = commentService.save(new CommentSaveRequest(commentText, bookName));
        return String.format("Comment successfully created: %s", commentMapper.toDTO(comment));
    }

    @ShellMethod(value = "Update comment: comment id, comment text", key = {"uc", "updateComment"})
    public String updateCommentWithBookId(@ShellOption String commentId, @ShellOption String commentText) {
        Comment comment = commentService.save(new CommentSaveRequest(Long.parseLong(commentId), commentText));
        return String.format("Comment successfully created: %s", commentMapper.toDTO(comment));
    }
    @ShellMethod(value = "Read all comments for book: book id", key = {"rc", "readComments"})
    public List<CommentDTO> readAllComments(@ShellOption String bookId) {
        return commentService.findByBookId(Long.parseLong(bookId)).stream()
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());
    }
    @ShellMethod(value = "Delete comment: comment id", key = {"dc", "deleteComment"})
    public String deleteComment(@ShellOption String commentId) {
        commentService.deleteById(Long.parseLong(commentId));
        return String.format("Comment with id = %s was successfully deleted", commentId);
    }


}
