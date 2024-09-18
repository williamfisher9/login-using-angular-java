package com.apps.security.dto;

public class UserResponseDTO {
    private Object items;
    private int status;

    public UserResponseDTO() {
    }

    public UserResponseDTO(Object items, int status) {
        this.items = items;
        this.status = status;
    }

    public Object getItems() {
        return items;
    }

    public void setItems(Object items) {
        this.items = items;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
