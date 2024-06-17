package org.vamshi.librarymanagementsystem.model.response;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class MyBooksResponse {
    private String bookName;
    private UUID transactionID;
    private Timestamp dueDate;
    private Timestamp returnDate;

    public MyBooksResponse(String bookName, UUID transactionID, Timestamp dueDate, Timestamp returnDate) {
        this.bookName = bookName;
        this.transactionID = transactionID;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }
}
