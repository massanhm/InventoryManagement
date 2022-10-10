package com.example.demo.service;

import java.util.Collections;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CustomUserDetails;
import com.example.demo.repository.LoginUserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final LoginUserMapper loginUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loginUserMapper.findByUsername(username)
                .map(
                        user -> new CustomUserDetails(
                                user.getUsername(),
                                user.getPassword(),
                                Collections.emptyList()
                        )
                )
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "ユーザーが見つかりません。(ユーザーネーム = '" + username + "')"
                        )
                );
    }

}
