package org.vamshi.librarymanagementsystem.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table
@Data
public class Publishers {
    @Id
    private UUID publisherId;
    private String publisherName;
}
