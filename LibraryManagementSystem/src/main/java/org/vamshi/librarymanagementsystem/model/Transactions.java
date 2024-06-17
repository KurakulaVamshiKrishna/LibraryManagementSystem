package org.vamshi.librarymanagementsystem.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Table
@Data
public class Transactions {
    @Id
    private UUID transactionID;
    private UUID referenceID;
    @ManyToOne
    @JoinColumn(name = "bookID", referencedColumnName = "BookID")
    private Books books;
    @ManyToOne
    @JoinColumn(name = "member_ID", referencedColumnName = "memeberID")
    private Members members;
    private String transactionType;
    private Timestamp dueDate;
    private Timestamp returnDate;
}
