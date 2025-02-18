package ru.skypro.homework.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;

    @Column(name = "author")
    private Integer authorId;

    @ManyToOne
    @JoinColumn(name = "author", referencedColumnName = "id", insertable = false, updatable = false)
    private User author;

    private Long createdAt;

    private String text;
}
