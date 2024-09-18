package com.apps.security.service;

import com.apps.security.dto.UserRequestDTO;
import com.apps.security.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User createUser(UserRequestDTO user);
    User findUserById(long id);
    User findUserByEmailAddress(String emailAddress);
    List<User> findAllUsers();
}
