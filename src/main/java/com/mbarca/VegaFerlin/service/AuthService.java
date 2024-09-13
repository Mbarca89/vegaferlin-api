package com.mbarca.VegaFerlin.service;

import com.mbarca.VegaFerlin.dto.request.LoginRequestDto;
import com.mbarca.VegaFerlin.dto.response.AuthResponseDto;
import com.mbarca.VegaFerlin.exceptions.NotFoundException;

public interface AuthService {
    public AuthResponseDto login (LoginRequestDto request) throws NotFoundException;
}
