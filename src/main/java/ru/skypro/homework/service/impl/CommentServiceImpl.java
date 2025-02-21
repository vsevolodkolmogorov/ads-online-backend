package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import java.time.Instant;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentMapper commentMapper, CommentRepository commentRepository,
                              AdRepository adRepository, UserRepository userRepository) {
        this.commentMapper = commentMapper;
        this.commentRepository = commentRepository;
        this.adRepository = adRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CommentDTO getCommentDTO(Comment comment) {
        return commentMapper.commentToCommentDTO(comment);
    }

    @Override
    public Comment createCommentFromDTO(CommentDTO commentDTO) {
        return commentMapper.commentDTOToComment(commentDTO);
    }

    @Override
    public CommentDTO addComment(Integer adId, CreateOrUpdateCommentDTO commentDTO, String username) {
        User author = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Ad ad = adRepository.findById(adId)
                .orElseThrow(() -> new RuntimeException("Ad not found"));

        Comment comment = new Comment()
                .setText(commentDTO.getText())
                .setAd(ad)
                .setAuthor(author)
                .setCreatedAt(Instant.now().getEpochSecond());

        commentRepository.save(comment);
        return commentMapper.commentToCommentDTO(comment);
    }

    @Override
    public void deleteComment(Integer commentId, String username) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!comment.getAuthor().getEmail().equals(username)) {
            throw new RuntimeException("You are not author of this comment");
        }

        commentRepository.delete(comment);
    }
}
