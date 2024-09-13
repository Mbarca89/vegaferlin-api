package com.mbarca.VegaFerlin.service.impl;

import com.mbarca.VegaFerlin.domain.User;
import com.mbarca.VegaFerlin.dto.request.LoginRequestDto;
import com.mbarca.VegaFerlin.dto.response.AuthResponseDto;
import com.mbarca.VegaFerlin.exceptions.NotFoundException;
import com.mbarca.VegaFerlin.repository.UserRepository;
import com.mbarca.VegaFerlin.service.AuthService;
import com.mbarca.VegaFerlin.service.JwtService;
import com.mbarca.VegaFerlin.utils.CryptoUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final CryptoUtils cryptoUtils;


    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, JwtService jwtService, CryptoUtils cryptoUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.cryptoUtils = cryptoUtils;
    }

    @Override
    public AuthResponseDto login(LoginRequestDto request) throws NotFoundException {

        String decryptedPassword;
        try {
            decryptedPassword = cryptoUtils.decrypt(request.getPassword());
        } catch (Exception e) {
            throw new RuntimeException("Failed to decrypt password", e);
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), decryptedPassword));

        User user = userRepository.findUserByName(request.getUserName());

        String token = jwtService.getToken(user);
        AuthResponseDto response = new AuthResponseDto();
        response.setUserName(user.getUsername());
        response.setToken(token);
        response.setRole(user.getAuthorities());
        response.setName(user.getName());
        response.setSurname(user.getSurname());
        response.setId(user.getId());
        return response;
    }
}
