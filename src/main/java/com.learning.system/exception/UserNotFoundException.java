package com.learning.system.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
   /* public UserNotFoundException(Long id) {
        super(id);
    }*/
}
