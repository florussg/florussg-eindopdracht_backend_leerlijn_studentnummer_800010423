package nl.florussg.eindopdracht_novi_backend_800010423.Controllers;

import nl.florussg.eindopdracht_novi_backend_800010423.Models.User;
import nl.florussg.eindopdracht_novi_backend_800010423.Services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    User userOne = new User();
    User userTwo = new User();

    List<User> users = new ArrayList<>();

    @BeforeEach
    public void setUp() {

        userOne.setUsername("florus");
        userOne.setPassword("password");
        userOne.addAuthority("ROLE_ASSISTANT");

        userTwo.setUsername("thomas");
        userTwo.setPassword("secret");

        users.add(userOne);
        users.add(userTwo);
    }

    @Test
    void getAllUsers() {

        when(userService.getUsers()).thenReturn(users);

        userController.getAllUsers();

        verify(userService, times(1)).getUsers();
    }

    @Test
    void getUserByUsername() {
        when(userService.getUser("florus")).thenReturn(userOne);

        userController.getUserByUsername("florus");

        verify(userService, times(1)).getUser("florus");
    }

    @Test
    void removeAuthority() {

        userController.removeAuthority("florus", "ROLE_ASSISTANT");
        verify(userService, times(1)).removeAuthority("florus", "ROLE_ASSISTANT");
    }

    @Test
    void addUserAuthority() {

    }

}