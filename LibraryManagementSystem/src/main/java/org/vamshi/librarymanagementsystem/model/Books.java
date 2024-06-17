package org.vamshi.librarymanagementsystem.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table
@Data
public class Books {
    @Id
    private UUID  BookID;

    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "AuthorId", referencedColumnName = "AuthorId")
    private Authors Author;

    private Long ISBN;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_Id",referencedColumnName = "genreId")
    private Genres genres;

    private Timestamp publicationYear;

    private int quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "publisher_id", referencedColumnName = "publisherId")
    private Publishers publishers;
}
