package com.apps.security.security;

import com.apps.security.model.User;
import com.apps.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository repository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByEmailAddress(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " was not found!"));

        return new org.springframework.security.core.userdetails.User(user.getEmailAddress(),
                user.getPassword(), user.getRoles());
    }
}