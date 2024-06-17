package org.vamshi.librarymanagementsystem.model.response;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class TransactionResponce {
    private UUID transactionID;
    private UUID referenceID;
    private UUID memberID;
    private UUID bookID;
    private Timestamp dueDate;
    private Timestamp returnDate;

    public TransactionResponce(UUID transactionID, UUID referenceID, UUID memberID, UUID bookID, Timestamp dueDate, Timestamp returnDate) {
        this.transactionID = transactionID;
        this.referenceID = referenceID;
        this.memberID = memberID;
        this.bookID = bookID;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }
}
