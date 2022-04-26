package nl.florussg.eindopdracht_novi_backend_800010423.Dto;

public class UserDto {

    private long id;

    private String username;

    private String password;

    private boolean enabled = true;

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
}
