package com.example.demo.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.User;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO users (username, password) values (#{username}, #{password})")
    void create(@Param("username") String username, @Param("password") String password);

    @Select("select * from users where username = #{username}")
    Optional<User> findByUsername(String username);
}
