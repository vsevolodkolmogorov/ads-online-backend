package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CommentDTO {

    private int author;
    private String authorImage;
    private String authorFirstName;
    private Long createdAt;
    private int pk;
    private String text;
}
