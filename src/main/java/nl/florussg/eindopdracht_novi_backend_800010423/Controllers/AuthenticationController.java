package nl.florussg.eindopdracht_novi_backend_800010423.Controllers;

import nl.florussg.eindopdracht_novi_backend_800010423.Models.AuthenticationRequest;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.AuthenticationResponse;
import nl.florussg.eindopdracht_novi_backend_800010423.Services.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {


    UserAuthenticationService userAuthenticationService;

    @Autowired
    public AuthenticationController(UserAuthenticationService userAuthenticationService) {
        this.userAuthenticationService = userAuthenticationService;
    }

    //creation of a JWT token
    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {

        AuthenticationResponse authenticationResponse = userAuthenticationService.authenticateUser(authenticationRequest);

        return ResponseEntity.ok(authenticationResponse);
    }

}



