package com.example.demo.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class User {

    private int id;

    private String username;

    private String email;

    private String password;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
