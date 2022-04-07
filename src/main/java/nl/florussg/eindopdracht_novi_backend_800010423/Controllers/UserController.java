package nl.florussg.eindopdracht_novi_backend_800010423.Controllers;

import nl.florussg.eindopdracht_novi_backend_800010423.Dto.UserDto;
import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.BadRequestException;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.User;
import nl.florussg.eindopdracht_novi_backend_800010423.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping (value = "/users")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getAllUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping (value = "/users/{username}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getUserByUsername(
            @PathVariable String username) {
        return ResponseEntity.ok(userService.getUser(username));
    }

    //TODO: UserDto verder inrichten!
    @PostMapping (value = "users/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> addNewUser (@RequestBody UserDto user ) {

        String newId = userService.addNewUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{Id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping (value = "/users/{username}/authorities")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> addUserAuthority(
            @PathVariable("username") String username,
            @RequestBody Map<String, Object> fields) {

        try {
            String authorityName = (String) fields.get("authority");
            //String authorityName = String.valueOf(fields.get("authority"));
            userService.addAuthority(username, authorityName);
            return ResponseEntity.noContent().build();
        }
        catch (Exception ex) {
            throw new BadRequestException("Error in adding an authority");
            }
    }

}
