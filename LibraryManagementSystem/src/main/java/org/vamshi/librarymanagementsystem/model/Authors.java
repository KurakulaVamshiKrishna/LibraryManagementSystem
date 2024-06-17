package org.vamshi.librarymanagementsystem.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Entity
@Table
@Data
public class Authors {
    @Id
    private UUID AuthorId;
    private String authorName;
}
