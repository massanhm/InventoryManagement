package com.example.demo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.demo.repository.UserMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final UserMapper userMapper;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return userMapper.findByUsername(value).isEmpty();
    }
}
