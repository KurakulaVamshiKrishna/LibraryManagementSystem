package org.vamshi.librarymanagementsystem.model.response;

import lombok.Data;

import java.util.UUID;

@Data
public class TransactionRequest {
    private UUID bookID;
    private UUID memberID;
    private String transactionType;
    private UUID referenceID;

    public TransactionRequest(UUID bookID, UUID memberID, String transactionType, UUID referenceID) {
        this.bookID = bookID;
        this.memberID = memberID;
        this.transactionType = transactionType;
        this.referenceID = referenceID;
    }
}
