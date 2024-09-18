package com.apps.security.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = -92983L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String emailAddress;

    private String password;

    public User() {
    }

    public User(String firstName, String lastName, String emailAddress, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(emailAddress, user.emailAddress) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, emailAddress, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
