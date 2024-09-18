package com.apps.security.service;

import com.apps.security.dto.UserRequestDTO;
import com.apps.security.model.User;
import com.apps.security.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService{
    private final UserRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository repository){
        this.repository = repository;
    }

    @Override
    public User createUser(UserRequestDTO userRequestDTO) {
        User userModel = modelMapper.map(userRequestDTO, User.class);
        return repository.save(userModel);
    }

    @Override
    public User findUserById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public User findUserByEmailAddress(String emailAddress) {
        return repository.findByEmailAddress(emailAddress);
    }

    @Override
    public List<User> findAllUsers() {
        return repository.findAll();
    }
}
