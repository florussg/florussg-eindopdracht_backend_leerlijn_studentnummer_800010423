package nl.florussg.eindopdracht_novi_backend_800010423.Dto;

import nl.florussg.eindopdracht_novi_backend_800010423.Models.Authority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class UserDto {


    private long id;

    private String username;

    private String password;

    private boolean enabled = true;

    private Set<String> authorities;

    //getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }
}
