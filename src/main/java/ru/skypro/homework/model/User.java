package ru.skypro.homework.model;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.skypro.homework.dto.RoleDTO;

import javax.persistence.*;
import java.util.List;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String email;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String phone;

    @Enumerated(EnumType.STRING)
    private RoleDTO role;

    private String image;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ad> ads;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;
}
