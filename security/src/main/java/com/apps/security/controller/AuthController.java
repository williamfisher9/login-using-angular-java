package com.apps.security.controller;

import com.apps.security.dto.UserRequestDTO;
import com.apps.security.dto.UserResponseDTO;
import com.apps.security.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(path = "/api/v1/auth")
public class AuthController {
    private final UserService service;

    @Autowired
    public AuthController(UserService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/register")
    public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody UserRequestDTO user){
        UserResponseDTO responseDTO = service.registerUser(user);
        return new ResponseEntity<>(responseDTO, HttpStatus.valueOf(responseDTO.getStatus()));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/login")
    public ResponseEntity<UserResponseDTO> loginUser(HttpServletRequest request){
        UserResponseDTO responseDTO = service.loginUser(request);
        return new ResponseEntity<>(responseDTO, HttpStatus.valueOf(responseDTO.getStatus()));
    }
}
