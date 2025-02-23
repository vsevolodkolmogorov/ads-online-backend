package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.model.Comment;

public interface CommentService {
    CommentDTO getCommentDTO (Comment comment);
    Comment createCommentFromDTO (CommentDTO commentDTO);

    CommentDTO addComment(Integer adId, CreateOrUpdateCommentDTO commentDTO, String username);

    void deleteComment(Integer commentId, String username);

    CommentsDTO getComments(Integer adId);

    CommentDTO updateComment(Integer commentId, CreateOrUpdateCommentDTO commentDTO);
}
