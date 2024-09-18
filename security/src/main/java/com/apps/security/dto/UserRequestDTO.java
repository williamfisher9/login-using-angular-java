package com.apps.security.dto;

import jakarta.validation.constraints.NotEmpty;

public class UserRequestDTO {
    @NotEmpty(message = "first name field of user entity is required")
    private String firstName;

    @NotEmpty(message = "last name field of user entity is required")
    private String lastName;

    @NotEmpty(message = "email address field of user entity is required")
    private String emailAddress;

    @NotEmpty(message = "password field of user entity is required")
    private String password;

    public UserRequestDTO() {
    }

    public UserRequestDTO(String firstName, String lastName, String emailAddress, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
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
}
