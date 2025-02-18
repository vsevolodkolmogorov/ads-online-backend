package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public CommentDTO getCommentDTO(Comment comment) {
        return commentMapper.commentToCommentDTO(comment);
    }

    public Comment createCommentFromDTO(CommentDTO commentDTO) {
        return commentMapper.commentDTOToComment(commentDTO);
    }
}
