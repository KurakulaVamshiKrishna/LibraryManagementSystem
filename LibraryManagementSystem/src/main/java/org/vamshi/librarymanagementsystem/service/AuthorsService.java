package org.vamshi.librarymanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vamshi.librarymanagementsystem.ProjectExceptions.RaiseException;
import org.vamshi.librarymanagementsystem.repository.AuthorsRepo;

import java.util.UUID;

@Service
public class AuthorsService {
    @Autowired
    AuthorsRepo authorsRepo;
    public Object getAllAuthor(){
        try {
            if(authorsRepo.findAll().isEmpty()){
                return new RaiseException("No Authors Found...","LMS404");
            }
            return authorsRepo.findAll();
        }catch (Exception  e){
            return new RaiseException("Internal Server Error","LMS500");
        }
    }

    public Object getAuthorsByName(String name){
        try {
            if (authorsRepo.findByAuthorName(name) == null) {
                return new RaiseException("No Author Found...", "LMS404");
            }
            return authorsRepo.findByAuthorName(name);
        }catch (Exception e){
            return new RaiseException("Provided input's {query params or path params} is/are Invalid!.,", "LMS404");
        }
    }

    public Object getAuthorsByID(UUID authorID){
        try {
            if (authorsRepo.findAll().isEmpty()) {
                return new RaiseException("No Authors Founds...", "LMS404");
            }
            return authorsRepo.findById(authorID);
        }catch (Exception e){
            return new RaiseException("Provided input's {query params or path params} is/are Invalid!.,", "LMS404");
        }
    }
}
