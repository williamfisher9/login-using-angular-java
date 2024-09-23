package com.apps.security.service;

import com.apps.security.dto.UserRequestDTO;
import com.apps.security.dto.UserResponseDTO;
import com.apps.security.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
