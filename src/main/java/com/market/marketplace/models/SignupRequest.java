package com.market.marketplace.models;

import java.util.Set;

public class SignupRequest {

    private String username;
    private Set<String> role;
    private String password;

    public SignupRequest() {
    }

    public SignupRequest(String username,String password, Set<String> role) {
        this.username = username;
        this.role = role;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
