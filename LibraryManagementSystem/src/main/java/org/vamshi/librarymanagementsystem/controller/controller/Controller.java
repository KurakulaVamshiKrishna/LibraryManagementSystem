package org.vamshi.librarymanagementsystem.controller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vamshi.librarymanagementsystem.ProjectExceptions.RaiseException;
import org.vamshi.librarymanagementsystem.model.Books;
import org.vamshi.librarymanagementsystem.model.Members;
import org.vamshi.librarymanagementsystem.model.response.TransactionRequest;
import org.vamshi.librarymanagementsystem.service.AuthorsService;
import org.vamshi.librarymanagementsystem.service.BooksService;
import org.vamshi.librarymanagementsystem.service.MemberService;
import org.vamshi.librarymanagementsystem.service.TransactionService;

import java.lang.reflect.Member;
import java.util.UUID;

@RestController
public class Controller {

    @Autowired
    BooksService booksService;

    @Autowired
    MemberService memberService;

    @Autowired
    AuthorsService authorsService;

    @Autowired
    TransactionService transactionService;


    //Books:
    @PostMapping("/addBook")
    public Object addBooks(@RequestBody Books books){
        return booksService.addBooksToLibrary(books);
    }

    @GetMapping("/getAllBooks")
    public Object getBooks(){
        return booksService.getAllBooks();
    }

    @GetMapping("/getBooksByGenres")
    public Object getBooksByGenres(@RequestParam String name){
        return booksService.getBooksByGenresName(name);
    }

    @GetMapping("/getBooksByAuthorNameOrAuthorID")
    public Object getBooksByAuthorIdOrAuthorName(@RequestParam(required = false) String authorName, @RequestParam(required = false) UUID authorID){
        if (authorName == null && authorID == null) {
            return new RaiseException("Required Query parameter's are not provided!.", "HCTB400");
        }else {
            return booksService.getBookByAuthorNameOrAuthorID(authorName,authorID);
        }
    }



    //Authors:
    @GetMapping("/getAuthors")
    public Object getAllAuthors(){
        return authorsService.getAllAuthor();
    }

    @GetMapping("/getAuthorByName")
    public Object getAuthorByName(@RequestParam(required = false) String name){
        return authorsService.getAuthorsByName(name);
    }

    @GetMapping
    public Object getAuthorById(@RequestParam(required = false) UUID authorId){
        return authorsService.getAuthorsByID(authorId);
    }




    //Members:
    @PostMapping("/addMembers")
    public Object addMembers(@RequestBody Members member){
        return memberService.addMember(member);
    }

    @GetMapping("/getAllMembers")
    public Object getAllMember(){
        return memberService.getAllMembers();
    }

    @GetMapping("/getMemeberByName")
    public Object getMemberByNames(@RequestParam(required = false) String name){
        return memberService.getMemberByName(name);
    }

    @GetMapping("/getMyBooks")
    public Object getMyBook(@RequestParam UUID memberID){
        return memberService.getMyBooks(memberID);
    }


    //Transaction:
    @PostMapping("/transaction")
    public Object createTraction(@RequestBody TransactionRequest transaction){
        return transactionService.libraryTransaction(transaction);
    }

    @GetMapping("/getTransactionByID")
    public Object getTransactionByID(@RequestParam UUID transactionID){
        return transactionService.getTransactionByTransactionID(transactionID);
    }

    @GetMapping("/getTransactionsByRefID")
    public Object getTransactionsByrefID(@RequestParam UUID referenceID){
        return transactionService.getTransactionByReferenceID(referenceID);
    }
}
