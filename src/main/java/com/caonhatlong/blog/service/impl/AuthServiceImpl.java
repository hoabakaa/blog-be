package com.caonhatlong.blog.service.impl;

import com.caonhatlong.blog.dto.RegisterRequest;
import com.caonhatlong.blog.model.User;
import com.caonhatlong.blog.repository.UserRepository;
import com.caonhatlong.blog.service.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;
    @Override
    public void signUp(RegisterRequest registerRequest) throws JsonProcessingException {
        User user = new User();
        user.setUserName(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        user.setEmail(registerRequest.getEmail());
        log.debug("user save: {}", objectMapper.writeValueAsString(registerRequest));
        userRepository.save(user);
    }
}
