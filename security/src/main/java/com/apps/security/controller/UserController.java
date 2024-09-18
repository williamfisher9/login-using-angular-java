package com.apps.security.controller;

import com.apps.security.dto.UserRequestDTO;
import com.apps.security.dto.UserResponseDTO;
import com.apps.security.model.User;
import com.apps.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(path = "/api/v1/users")
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO user){
        UserResponseDTO responseDTO = new UserResponseDTO();
        User newUser = service.createUser(user);

        responseDTO.setItems(newUser);
        responseDTO.setStatus(newUser != null ? 200 : 400);

        return new ResponseEntity<>(responseDTO, newUser != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<UserResponseDTO> getAllUsers(){
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setItems(service.findAllUsers());
        responseDTO.setStatus(200);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
