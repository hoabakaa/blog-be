package com.caonhatlong.blog.service;

import com.caonhatlong.blog.dto.LoginRequest;
import com.caonhatlong.blog.dto.RegisterRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface AuthService {

    void signUp(RegisterRequest registerRequest) throws JsonProcessingException;

    String login(LoginRequest loginRequest);
}
