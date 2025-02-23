package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
public class CommentsDTO {

    private int count;
    private CommentDTO comment;
    private List<CommentDTO> results;

    public CommentsDTO(int count, List<CommentDTO> results) {
        this.count = count;
        this.results = results;
    }
}
