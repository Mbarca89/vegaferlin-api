package com.mbarca.VegaFerlin.repository.impl;

import com.mbarca.VegaFerlin.domain.User;
import com.mbarca.VegaFerlin.exceptions.NotFoundException;
import com.mbarca.VegaFerlin.repository.UserRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Objects;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final String CREATE_USER = "INSERT INTO Users (name, surname, user_name, password, role) VALUES (?, ?, ?, ?, ?)";
    private final String DELETE_USER = "DELETE FROM Users WHERE user_name = ?";
    final String FIND_USER_BY_NAME = "SELECT * FROM Users WHERE user_name = ?";
    final String FIND_USER_BY_ID = "SELECT * FROM Users WHERE id = ?";
    private final String GET_ALL_USERS = "SELECT * FROM Users";
    private final String EDIT_USER = "UPDATE users SET name = ?, surname = ?, password = ?, role = ?, area = ? WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer createUser(User user) {
        return jdbcTemplate.update(CREATE_USER,
                user.getName(),
                user.getSurname(),
                user.getUserName(),
                user.getPassword(),
                user.getRole());
    }

    @Override
    public Integer deleteUser(String userName) {
        return jdbcTemplate.update(DELETE_USER, userName);
    }

    public User findUserByName(String userName) throws NotFoundException {
        Object[] params = {userName};
        int[] types = {Types.VARCHAR};
        try {
            return jdbcTemplate.queryForObject(FIND_USER_BY_NAME, params, types, new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Usuario no encontrado");
        }
    }

    @Override
    public List<User> getUsers() {
        return jdbcTemplate.query(GET_ALL_USERS, new UserRowMapper());
    }

    @Override
    public Integer editUser(User newUser) throws NotFoundException {
        User edituser = new User();

        Object[] params = {newUser.getId()};
        int[] types = {1};
        User currentUser = jdbcTemplate.queryForObject(FIND_USER_BY_ID, params, types, new UserRowMapper());

        if (currentUser == null) {
            throw new NotFoundException("Usuario no encontrado!");
        }

        edituser.setName(currentUser.getUserName());
        if (!newUser.getName().isEmpty()) edituser.setName(newUser.getName());
        else edituser.setName(currentUser.getName());
        if (!newUser.getSurname().isEmpty()) edituser.setSurname(newUser.getSurname());
        else edituser.setSurname(currentUser.getSurname());
        if (!newUser.getUserName().isEmpty()) edituser.setUserName(newUser.getUserName());
        else edituser.setUserName(currentUser.getUserName());
        if (!newUser.getPassword().isEmpty()) edituser.setPassword(newUser.getPassword());
        else edituser.setPassword(currentUser.getPassword());
        if (!newUser.getRole().isEmpty()) edituser.setRole(newUser.getRole());
        else edituser.setRole(currentUser.getRole());


        return jdbcTemplate.update(EDIT_USER, edituser.getName(), edituser.getSurname(), edituser.getPassword(), edituser.getRole(), newUser.getId());
    }

    static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setUserName(rs.getString("user_name").toLowerCase());
            user.setRole(rs.getString("role"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    }
}
