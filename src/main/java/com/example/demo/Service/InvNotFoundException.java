package com.example.demo.Service;

public class InvNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvNotFoundException(String message) {
        super(message);
    }

}
