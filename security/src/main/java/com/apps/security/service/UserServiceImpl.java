package com.apps.security.service;

import com.apps.security.dto.UserRequestDTO;
import com.apps.security.dto.UserResponseDTO;
import com.apps.security.enums.RoleType;
import com.apps.security.exception.AuthorizationHeaderNotFoundException;
import com.apps.security.exception.RoleDoesNotExistException;
import com.apps.security.model.Role;
import com.apps.security.model.User;
import com.apps.security.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository repository, PasswordEncoder encoder, AuthenticationManager authenticationManager, ModelMapper modelMapper){
        this.repository = repository;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO) {
        Set<String> requestRoles = userRequestDTO.getRoles();
        Set<Role> userRoles = new HashSet<>();
        for(String roleName:requestRoles){
            if(RoleType.existsByName(roleName)){
                userRoles.add(new Role(RoleType.getRoleTypeByName(roleName)));
            } else {
                throw new RoleDoesNotExistException("Role " + roleName + " does not exist!");
            }
        }

        UserResponseDTO responseDTO = new UserResponseDTO();
        User createdUser = null;

        if(!userRoles.isEmpty()){
            User user = modelMapper.map(userRequestDTO, User.class);
            user.setPassword(encoder.encode(user.getPassword()));
            user.setRoles(userRoles);

            createdUser = repository.save(user);
            responseDTO.setItems(createdUser);
            responseDTO.setStatus(createdUser.getId() > 0 ? HttpStatus.OK.value() : HttpStatus.EXPECTATION_FAILED.value());
            return responseDTO;
        }

        responseDTO.setItems(null);
        responseDTO.setStatus(HttpStatus.EXPECTATION_FAILED.value());
        return responseDTO;
    }

    @Override
    public User findUserById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public User findUserByEmailAddress(String emailAddress) {
        return repository.findByEmailAddress(emailAddress)
                .orElseThrow(() -> new UsernameNotFoundException(emailAddress + " Not Found"));
    }

    @Override
    public List<User> findAllUsers() {
        return repository.findAll();
    }

    @Override
    public UserResponseDTO loginUser(HttpServletRequest request) {
        if(request.getHeader("Authorization").isEmpty()){
            throw new AuthorizationHeaderNotFoundException("Authorization header is empty!");
        } else {

            String authorizationHeader = request.getHeader("Authorization")
                    .substring("Basic".length()).trim();

            String decodedAuthorizationHeader = new String(Base64.getDecoder().decode(authorizationHeader));
            String[] credentials = decodedAuthorizationHeader.split(":");

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentials[0], credentials[1])
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserResponseDTO responseDTO = new UserResponseDTO();
            responseDTO.setItems(authentication);
            responseDTO.setStatus(authentication.isAuthenticated() ? HttpStatus.OK.value() : HttpStatus.UNAUTHORIZED.value());

            return responseDTO;
        }
    }
}
