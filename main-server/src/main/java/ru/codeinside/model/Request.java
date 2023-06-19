package ru.codeinside.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "requests")
@Getter
@Setter
@NoArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "request_book_id")
    private Book book;
    @ManyToOne
    @JoinColumn(name = "request_requester_id")
    private User requester;
    @Column(name = "request_created")
    @CreationTimestamp
    private LocalDateTime created;
    @Enumerated(EnumType.STRING)
    @Column(name = "request_status")
    private RequestStatus status;
    @OneToMany(mappedBy = "request")
    private Set<Comment> comments = new HashSet<>();

    public enum RequestStatus {
        PENDING,
        CANCELED,
        CONFIRMED,
        REJECTED
    }
}