package org.vamshi.librarymanagementsystem.model.response;

import lombok.Data;
import org.vamshi.librarymanagementsystem.model.Books;

import java.util.UUID;

@Data
public class BooksResponse{
    private UUID BookID;
    private String Booktitle;
    private String AuthorName;
    private String PublisherName;
    private int Quantity;

    public BooksResponse() {
    }

    public BooksResponse(UUID bookID, String bookTitle, String authorName, String publisherName, int quantity) {
        BookID = bookID;
        Booktitle = bookTitle;
        AuthorName = authorName;
        PublisherName = publisherName;
        Quantity = quantity;
    }
}
