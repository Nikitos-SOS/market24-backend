package com.market.marketplace.models;

import java.util.Set;

public class SignupRequest {

    private String userName;
    private Set<String> role;
    private String password;

    public SignupRequest() {
    }

    public SignupRequest(String userName,String password, Set<String> role) {
        this.userName = userName;
        this.role = role;
        this.password = password;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}
