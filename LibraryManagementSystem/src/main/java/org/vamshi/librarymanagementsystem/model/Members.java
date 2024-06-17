package org.vamshi.librarymanagementsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Entity
@Table
@Data
public class Members {
    @Id
    private UUID memeberID;
    private String name;
    private String address;
    private Long phoneNumber;
    private String email;
}
