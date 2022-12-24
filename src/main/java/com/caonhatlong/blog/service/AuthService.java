package com.caonhatlong.blog.service;

import com.caonhatlong.blog.dto.AuthenticationResponse;
import com.caonhatlong.blog.dto.LoginRequest;
import com.caonhatlong.blog.dto.RegisterRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface AuthService {

    void signUp(RegisterRequest registerRequest) throws JsonProcessingException;

    AuthenticationResponse login(LoginRequest loginRequest);

    Optional<User> getCurrentUser();
}
