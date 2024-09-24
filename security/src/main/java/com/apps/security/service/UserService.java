package com.apps.security.service;

import com.apps.security.dto.UserRequestDTO;
import com.apps.security.dto.UserResponseDTO;
import com.apps.security.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserResponseDTO registerUser(UserRequestDTO user);
    User findUserById(long id);
    User findUserByEmailAddress(String emailAddress);
    List<User> findAllUsers();
    UserResponseDTO loginUser(HttpServletRequest request);
}
