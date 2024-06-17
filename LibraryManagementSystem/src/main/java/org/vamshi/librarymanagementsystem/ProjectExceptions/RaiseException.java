package org.vamshi.librarymanagementsystem.ProjectExceptions;

import lombok.Data;

@Data
public class RaiseException{
    private String message;
    private String status;

    public RaiseException(String message, String status) {
        this.message = message;
        this.status = status;
    }
}
