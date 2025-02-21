package ru.skypro.homework.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;

    @Column(name = "author")
    private Integer authorId;

    @Column(name = "ad_id")
    private Integer adId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author", referencedColumnName = "id", insertable = false, updatable = false)
    private User author;

    private Long createdAt;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ad_id", referencedColumnName = "pk", insertable = false, updatable = false)
    private Ad ad;
}
