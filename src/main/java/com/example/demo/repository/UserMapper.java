package com.example.demo.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO users (username, password) values (#{username}, #{password})")
    void create(@Param("username") String username, @Param("password") String password);
}
