package ru.skypro.homework.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;

    @Column(name = "author")
    private Integer authorId;

    @ManyToOne
    @JoinColumn(name = "author", referencedColumnName = "id", insertable = false, updatable = false)
    private User author;

    private String image;

    private Integer price;

    private String title;
}
