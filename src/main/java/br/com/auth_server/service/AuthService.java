package br.com.auth_server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface AuthService {
    UsernamePasswordAuthenticationToken getAuthentication(String token) throws JsonProcessingException;
}
