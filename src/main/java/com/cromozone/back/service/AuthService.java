package com.cromozone.back.service;

import com.cromozone.back.DTO.LoginRequest;
import com.cromozone.back.DTO.LoginResponse;
import com.cromozone.back.DTO.RegisterRequest;

public interface AuthService {

    void register(RegisterRequest request); // POST /auth/register

    LoginResponse login(LoginRequest request); // POST /auth/login

    void logout(String token); // POST /auth/logout (si usas lista negra, etc.)
}