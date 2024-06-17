package org.vamshi.librarymanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vamshi.librarymanagementsystem.ProjectExceptions.RaiseException;
import org.vamshi.librarymanagementsystem.model.Transactions;
import org.vamshi.librarymanagementsystem.model.response.TransactionRequest;
import org.vamshi.librarymanagementsystem.model.response.TransactionResponce;
import org.vamshi.librarymanagementsystem.repository.BooksRepo;
import org.vamshi.librarymanagementsystem.repository.MembersRepo;
import org.vamshi.librarymanagementsystem.repository.TransactionsRepo;
import org.vamshi.librarymanagementsystem.support.DueDate;


import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    TransactionsRepo transactionsRepo;

    @Autowired
    BooksRepo booksRepo;

    @Autowired
    MembersRepo membersRepo;

    public Object libraryTransaction(TransactionRequest transactionRequest) {
        try {
            Transactions transaction = new Transactions();
            UUID transactionID = UUID.randomUUID();
            DueDate date = new DueDate();
            Timestamp returnDate = new Timestamp(new Date().getTime());

            transaction.setTransactionID(transactionID);
            if (booksRepo.findById(transactionRequest.getBookID()).isEmpty()) {
                return new RaiseException("Provided input's {query params or path params} is/are Invalid!.,", "LMS404");
            }
            transaction.setBooks(booksRepo.findById(transactionRequest.getBookID()).get());
            if (membersRepo.findById(transactionRequest.getMemberID()).isEmpty()) {
                return new RaiseException("Provided input's {query params or path params} is/are Invalid!.,", "LMS404");
            } else {
                transaction.setMembers(membersRepo.findById(transactionRequest.getMemberID()).get());
            }
            if (transactionRequest.getTransactionType().equals("borrow") && booksRepo.findById(transactionRequest.getBookID()).get().getQuantity() > 0) {
                booksRepo.findById(transaction.getBooks().getBookID()).get().setQuantity(booksRepo.findById(transaction.getBooks().getBookID()).get().getQuantity() - 1);
                transaction.setReferenceID(UUID.randomUUID());
                transaction.setDueDate(date.getDueDate());
                transaction.setReturnDate(null);
            } else if (transactionRequest.getTransactionType().equals("return")) {
                booksRepo.findById(transaction.getBooks().getBookID()).get().setQuantity(booksRepo.findById(transaction.getBooks().getBookID()).get().getQuantity() + 1);
                transaction.setReferenceID(transactionRequest.getReferenceID());
                transaction.setDueDate(transactionsRepo.findByReferenceID(transactionRequest.getReferenceID()));
                transaction.setReturnDate(returnDate);
            }
            transaction.setTransactionType(transactionRequest.getTransactionType());
            transactionsRepo.save(transaction);
            TransactionResponce transactionResponce = new TransactionResponce(transactionID, transaction.getReferenceID(), transaction.getMembers().getMemeberID(), transaction.getBooks().getBookID(), transaction.getDueDate(), transaction.getReturnDate());
            return transactionResponce;
        } catch (Exception e) {
            return new RaiseException("Provided input's {query params or path params} is/are Invalid!.,", "LMS404");
        }
    }

    public Object getTransactionByTransactionID(UUID transactionID) {
        try {
            if (transactionsRepo.findById(transactionID).isEmpty()) {
                return new RaiseException("No Transaction Founds...", "LMS404");
            }
            return transactionsRepo.findById(transactionID).get();
        } catch (Exception e) {
            return new RaiseException("Provided input's {query params or path params} is/are Invalid!.,", "LMS404");
        }
    }

    public Object getTransactionByReferenceID(UUID referenceID) {
        try {
            if (transactionsRepo.findAllByReferenceID(referenceID).isEmpty()) {
                return new RaiseException("No Transaction Founds...", "LMS404");
            }
            return transactionsRepo.findAllByReferenceID(referenceID);
        } catch (Exception e) {
            return new RaiseException("Provided input's {query params or path params} is/are Invalid!.,", "LMS404");
        }
    }
}
