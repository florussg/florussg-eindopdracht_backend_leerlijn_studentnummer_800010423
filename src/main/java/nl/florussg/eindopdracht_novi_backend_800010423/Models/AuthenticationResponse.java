package nl.florussg.eindopdracht_novi_backend_800010423.Models;

public class AuthenticationResponse {


    // Response for authentication -> jwt token
    private final String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
