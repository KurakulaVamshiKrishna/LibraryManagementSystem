package org.vamshi.librarymanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vamshi.librarymanagementsystem.ProjectExceptions.RaiseException;
import org.vamshi.librarymanagementsystem.model.*;
import org.vamshi.librarymanagementsystem.model.response.BooksResponse;
import org.vamshi.librarymanagementsystem.repository.*;
import org.vamshi.librarymanagementsystem.support.GetRandom;

import java.nio.ByteBuffer;
import java.sql.Timestamp;
import java.util.*;


@Service
public class BooksService {
    @Autowired
    BooksRepo booksRepo;
    @Autowired
    GenresRepo genresRepo;
    @Autowired
    AuthorsRepo authorsRepo;
    @Autowired
    PublishersRepo publishersRepo;

    Timestamp time = new Timestamp(new Date().getTime());
    GetRandom random = new GetRandom();

    public Object addBooksToLibrary(Books books) {
        System.out.println(books);
        System.out.println(books);
        UUID BookID = UUID.randomUUID();
        UUID genreID;
        UUID authorID;
        UUID publisherId;
        if (genresRepo.findByGenreName(books.getGenres().getGenreName()) == null) {
            genreID = UUID.randomUUID();
        } else {
            genreID = genresRepo.findByGenreName(books.getGenres().getGenreName()).getGenreId();
        }
        if (authorsRepo.findByAuthorName(books.getAuthor().getAuthorName()) == null) {
            authorID = UUID.randomUUID();
        } else {
            authorID = authorsRepo.findByAuthorName(books.getAuthor().getAuthorName()).getAuthorId();
        }
        Long ISBN = random.getRandom();
        if (publishersRepo.findByPublisherName(books.getPublishers().getPublisherName()) == null) {
            publisherId = UUID.randomUUID();
        } else {
            publisherId = publishersRepo.findByPublisherName(books.getPublishers().getPublisherName()).getPublisherId();
        }
        books.setBookID(BookID);
        books.getAuthor().setAuthorId(authorID);
        books.setISBN(ISBN);
        books.getGenres().setGenreId(genreID);
        books.setPublicationYear(time);
        books.getPublishers().setPublisherId(publisherId);
        booksRepo.save(books);
        return new BooksResponse(BookID, books.getTitle(), books.getAuthor().getAuthorName(), books.getPublishers().getPublisherName(), books.getQuantity());
    }

    public Object getAllBooks() {
        if (booksRepo.findAll().isEmpty()) {
            return new RaiseException("No Books Founds...", "LMS404");
        }
        return booksRepo.findAll();
    }

    public Object getBooksByGenresName(String GenresName) {
        try {
            UUID genresID = genresRepo.findByGenreName(GenresName).getGenreId();
            if (booksRepo.findByGenresID(genresID).isEmpty()) {
                return new RaiseException("No Books are added...", "LMS404");
            }
            return booksRepo.findByGenresID(genresID);
        } catch (Exception e) {
            return new RaiseException("Provided input's {query params or path params} is/are Invalid!.,", "LMS404");
        }
    }

    public Object getBookByAuthorNameOrAuthorID(String authorName, UUID authorID) {
        try {
            if (authorID == null && authorName != null) {
                UUID id = authorsRepo.findByAuthorName(authorName).getAuthorId();
                if (booksRepo.findByAuthorID(id).isEmpty()) {
                    return new RaiseException("Provided input's {query params or path params} is/are Invalid!.,", "LMS404");
                } else
                    return booksRepo.findByAuthorID(id);
            } else {
                if (booksRepo.findByAuthorID(authorID).isEmpty()) {
                    return new RaiseException("Provided input's {query params or path params} is/are Invalid!.,", "LMS404");
                } else {
                    return booksRepo.findByAuthorID(authorID);
                }
            }
        } catch (Exception e) {
            return new RaiseException("Provided input's {query params or path params} is/are Invalid!.,", "LMS404");
        }

    }
}
