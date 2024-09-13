package com.mbarca.VegaFerlin.repository;

import com.mbarca.VegaFerlin.domain.User;
import com.mbarca.VegaFerlin.exceptions.NotFoundException;

import java.util.List;

public interface UserRepository {
    Integer createUser(User user);
    Integer deleteUser(String userName);
    User findUserByName(String userName) throws NotFoundException;
    List<User> getUsers();
    Integer editUser(User user) throws NotFoundException;

}
