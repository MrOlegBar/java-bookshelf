package ru.codeinside.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "bookshelfs")
@Getter
@Setter
public class Bookshelf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookshelf_id")
    private Long id;
    @Column(name = "bookshelf_name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "bookshelf_owner_id")
    private User owner;
    @OneToMany(mappedBy = "bookshelf")
    private Set<Book> books;
}