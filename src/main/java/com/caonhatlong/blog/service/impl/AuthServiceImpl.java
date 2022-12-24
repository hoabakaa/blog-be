package com.caonhatlong.blog.service.impl;

import com.caonhatlong.blog.dto.AuthenticationResponse;
import com.caonhatlong.blog.dto.LoginRequest;
import com.caonhatlong.blog.dto.RegisterRequest;
import com.caonhatlong.blog.model.User;
import com.caonhatlong.blog.repository.UserRepository;
import com.caonhatlong.blog.security.JWTProvider;
import com.caonhatlong.blog.service.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTProvider jwtProvider;
    @Override
    public void signUp(RegisterRequest registerRequest) throws JsonProcessingException {
        User user = new User();
        user.setUserName(registerRequest.getUsername());
        user.setPassword(encodePassword(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        log.debug("user save: {}", objectMapper.writeValueAsString(registerRequest));
        userRepository.save(user);
    }

    @Override
    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateTokent(authentication);
        return new AuthenticationResponse(token, loginRequest.getUsername());
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public Optional<org.springframework.security.core.userdetails.User> getCurrentUser(){
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.of(principal);
    }
}
