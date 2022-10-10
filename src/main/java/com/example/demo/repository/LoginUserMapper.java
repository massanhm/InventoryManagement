package com.example.demo.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.User;

@Mapper
public interface LoginUserMapper {

    @Select("select * from users where username = #{username}")
    Optional<User> findByUsername(String username);

}
