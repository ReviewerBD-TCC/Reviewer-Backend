package com.reviewer.reviewer.infra.exceptions;

public class UserException extends RuntimeException{
    public UserException (String message){
        super(message);
    }
}
