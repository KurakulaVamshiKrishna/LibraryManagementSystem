package org.vamshi.librarymanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vamshi.librarymanagementsystem.ProjectExceptions.RaiseException;
import org.vamshi.librarymanagementsystem.model.Books;
import org.vamshi.librarymanagementsystem.model.Members;
import org.vamshi.librarymanagementsystem.model.Transactions;
import org.vamshi.librarymanagementsystem.model.response.MyBooksResponse;
import org.vamshi.librarymanagementsystem.repository.BooksRepo;
import org.vamshi.librarymanagementsystem.repository.MembersRepo;
import org.vamshi.librarymanagementsystem.repository.TransactionsRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

@Service
public class MemberService {
    @Autowired
    MembersRepo membersRepo;

    @Autowired
    TransactionsRepo transactionsRepo;

    @Autowired
    BooksRepo booksRepo;
    public Object addMember(Members member){
        try{
            UUID memberID = UUID.randomUUID();
            member.setMemeberID(memberID);
            return membersRepo.save(member);
        }catch (Exception e){
            return new RaiseException("Provided input's {query params or path params} is/are Invalid!.,", "LMS404");
        }
    }

    public Object getAllMembers(){
        if(membersRepo.findAll().isEmpty()){
            return new RaiseException("No members are found or added...", "LMS404");
        }else {
            return membersRepo.findAll();
        }
    }

    public Object getMemberByName(String name){
        if(membersRepo.finfByName(name) == null){
            return new RaiseException("Provided input's {query params or path params} is/are Invalid!.,", "LMS404");
        }else {
            return membersRepo.finfByName(name);
        }
    }

    public Object getMyBooks(UUID memberID){
        try {
            if(membersRepo.findById(memberID).isEmpty()){
                return new RaiseException("Provided input's {query params or path params} is/are Invalid!.,", "LMS404");
            }
            List<Transactions> bookIDs = transactionsRepo.findBooksByMemberID(memberID);
            if(bookIDs.isEmpty()){
                return new RaiseException("No books are found or borrowed...", "LMS404");
            }
            List<MyBooksResponse> myBooks = new ArrayList<>();
            ListIterator<Transactions> list = bookIDs.listIterator();
            while (list.hasNext()) {
                Transactions books = list.next();
                myBooks.add(new MyBooksResponse(booksRepo.findById(books.getBooks().getBookID()).get().getTitle(),books.getTransactionID(), books.getDueDate(),books.getReturnDate()));
            }
            return myBooks;
        }catch (Exception e){
            return new RaiseException("Provided input's {query params or path params} is/are Invalid!.,", "LMS404");
        }
    }


}
