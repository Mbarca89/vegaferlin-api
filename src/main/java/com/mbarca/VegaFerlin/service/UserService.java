package com.mbarca.VegaFerlin.service;

import com.mbarca.VegaFerlin.dto.request.UserRequestDto;
import com.mbarca.VegaFerlin.dto.response.UserResponseDto;
import com.mbarca.VegaFerlin.exceptions.MissingDataException;
import com.mbarca.VegaFerlin.exceptions.NotFoundException;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserService {
    String createUser (UserRequestDto userRequestDto) throws MissingDataException, NoSuchAlgorithmException;
    String deleteUser (String name);
    List<UserResponseDto> getUsers();
    String editUser (UserRequestDto userRequestDto) throws MissingDataException, NoSuchAlgorithmException, NotFoundException;
    String getUserRealName (String username);
}
