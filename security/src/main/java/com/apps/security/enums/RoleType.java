package com.apps.security.enums;

import java.util.HashMap;
import java.util.Map;

public enum RoleType {
    ADMIN("ADMIN_ROLE", 1),
    USER("USER_ROLE", 2);

    private String type;
    private int code;

    private static final Map<String, RoleType> ROLE_TYPE_BY_NAME = new HashMap<>();

    static {
        for(RoleType type:values()){
            ROLE_TYPE_BY_NAME.put(type.getType(), type);
        }
    }

    RoleType(String type, int code){
        this.type = type;
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public int getCode() {
        return code;
    }

    public static boolean existsByName(String name){
        for(RoleType type:values()){
            if(type.getType().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    public static RoleType getRoleTypeByName(String name){
        for (Map.Entry<String,RoleType> entry : ROLE_TYPE_BY_NAME.entrySet()){
            if(entry.getKey().equalsIgnoreCase(name)){
                return entry.getValue();
            }
        }
        return null;
    }
}