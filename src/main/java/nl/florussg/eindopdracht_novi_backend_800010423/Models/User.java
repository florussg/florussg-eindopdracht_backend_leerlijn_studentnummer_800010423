package nl.florussg.eindopdracht_novi_backend_800010423.Models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    private String username;

    private String password;

//TODO: Nog verder af te maken
    //    @OneToMany(targetEntity = Authority.class, mappedBy = "username", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    //    private Set<Authority> authorities = new HashSet<>();


    //constructor
    public User() {}

    //setters and getters

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
}
