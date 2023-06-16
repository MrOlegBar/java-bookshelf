package ru.codeinside.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "books")
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;
    @Column(name = "book_title")
    private String title;
    @Column(name = "book_author")
    private String author;
    @ManyToOne
    @JoinColumn(name = "book_owner_id")
    private User owner;
    @ManyToMany(mappedBy = "booksToRead")
    private Set<User> users;
    @Column(name = "book_is_available")
    private Boolean isAvailable;
    @Column(name = "book_available_before")
    private LocalDateTime availableBefore;
    @ManyToOne
    @JoinColumn(name = "book_bookshelf_id")
    private Bookshelf bookshelf;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return (this.id != null && id.equals(book.id));
    }

    @Override
    public int hashCode() {
        return this.id != null ? this.id.hashCode() : 0;
    }
}
