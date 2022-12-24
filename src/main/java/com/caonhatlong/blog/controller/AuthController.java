package com.caonhatlong.blog.controller;

import com.caonhatlong.blog.dto.AuthenticationResponse;
import com.caonhatlong.blog.dto.LoginRequest;
import com.caonhatlong.blog.dto.RegisterRequest;
import com.caonhatlong.blog.service.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    private final ObjectMapper objectMapper;

    private final AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody RegisterRequest registerRequest) throws JsonProcessingException {
        log.debug("Register Request : {}", objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(registerRequest));
        authService.signUp(registerRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }
}
