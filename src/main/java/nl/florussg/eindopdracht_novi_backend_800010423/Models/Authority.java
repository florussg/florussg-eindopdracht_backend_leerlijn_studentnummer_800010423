package nl.florussg.eindopdracht_novi_backend_800010423.Models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "authorities")
@IdClass(AuthorityKey.class)
public class Authority implements Serializable {

    //attributes
    @Id
    @Column(nullable = false)
    private String username;

    @Id
    @Column(nullable = false)
    private String authority;

    public Authority() {
            }


    //getters and setters


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
