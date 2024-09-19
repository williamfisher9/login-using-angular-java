package com.apps.security.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public class UserRequestDTO {
    @NotEmpty(message = "first name field of user entity is required")
    private String firstName;

    @NotEmpty(message = "last name field of user entity is required")
    private String lastName;

    @NotEmpty(message = "email address field of user entity is required")
    private String emailAddress;

    @NotEmpty(message = "password field of user entity is required")
    private String password;

    @NotNull(message = "Roles field is required!")
    private Set<String> roles;

    public UserRequestDTO() {
    }

    public @NotEmpty(message = "first name field of user entity is required") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotEmpty(message = "first name field of user entity is required") String firstName) {
        this.firstName = firstName;
    }

    public @NotEmpty(message = "last name field of user entity is required") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotEmpty(message = "last name field of user entity is required") String lastName) {
        this.lastName = lastName;
    }

    public @NotEmpty(message = "email address field of user entity is required") String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(@NotEmpty(message = "email address field of user entity is required") String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public @NotEmpty(message = "password field of user entity is required") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "password field of user entity is required") String password) {
        this.password = password;
    }

    public @NotNull(message = "Roles field is required!") Set<String> getRoles() {
        return roles;
    }

    public void setRoles(@NotNull(message = "Roles field is required!") Set<String> roles) {
        this.roles = roles;
    }
}
