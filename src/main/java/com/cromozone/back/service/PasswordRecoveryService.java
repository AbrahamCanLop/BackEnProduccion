package com.cromozone.back.service;

import com.cromozone.back.DTO.PasswordResetRequest;
import com.cromozone.back.DTO.PasswordForgotRequest;

public interface PasswordRecoveryService {

    void solicitarRecuperacion(PasswordForgotRequest request); // POST /auth/forgot-password

    void resetearContrasena(PasswordResetRequest request); // POST /auth/reset-password
}
