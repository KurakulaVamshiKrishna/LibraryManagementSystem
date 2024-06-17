package org.vamshi.librarymanagementsystem.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table
@Data
public class Genres {
    @Id
    private UUID genreId;
    private String genreName;
}
