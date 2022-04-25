package nl.florussg.eindopdracht_novi_backend_800010423.Services;

import nl.florussg.eindopdracht_novi_backend_800010423.Dto.UserDto;
import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.BadRequestException;
import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.RecordNotFoundException;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Authority;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.User;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.UserRepository;
import nl.florussg.eindopdracht_novi_backend_800010423.Config.WebSecurityConfigTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Import({WebSecurityConfigTest.class})
@ExtendWith(MockitoExtension.class)
@WithMockUser(username = "myUser", roles = { "myAuthority" }) //TODO: Checken hoe ik users mock, anders weghalen en afronden!
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    User userOne = new User();
    User userTwo = new User();
    User userThree = new User();
    UserDto userDto = new UserDto();
    List<User> users = new ArrayList<>();

    @BeforeEach
    public void setUp() {

        userOne.setUsername("florus");
        userOne.setPassword("password");
        userOne.addAuthority("ROLE_ADMIN");

        userTwo.setUsername("peter");
        userTwo.setPassword("password");
        userTwo.addAuthority("ROLE_ASSISTANT");

        userThree.setUsername("fred");
        userThree.setPassword("password");
        userThree.addAuthority("ROLE_MECHANIC");

        userDto.setUsername("florus");
        userDto.setPassword("password");

        users.add(userOne);
        users.add(userTwo);
        users.add(userThree);

        var autoritiesX = userOne.getAuthorities();
    }


    @Test
    void getUsers() {

        when(userRepository.findAll()).thenReturn(users);

        Iterable<User> foundUsers = userService.getUsers();

        verify(userRepository, times(1)).findAll();

        assertThat(foundUsers).isEqualTo(users);
    }

    @Test
    void getUser() {

        when(userRepository.findByUsername("florus")).thenReturn(Optional.of(userOne));

        User userFound = userService.getUser("florus");
        verify(userRepository, times(1)).findByUsername("florus");

        assertThat(userFound).isEqualTo(userOne);
    }

    @Test
    void getUserException() {
        assertThrows(RecordNotFoundException.class, () -> userService.getUser("jan"));
    }

    @Test
    void addNewUser() {

        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(userOne);

        User userToAdd = userRepository.save(userOne);
        String username = userService.addNewUser(userDto);

        assertThat(username).isSameAs(userToAdd.getUsername());
    }

    @Test
    void addAuthority() {

        when(userRepository.findByUsername("peter")).thenReturn(Optional.ofNullable(userTwo));
        when(userRepository.save(userTwo)).thenReturn(userTwo);

        User userPartialEdit = userService.addAuthority("peter", "ROLE_MECHANIC");

        verify(userRepository, times(2)).findByUsername("peter");
        verify(userRepository, times(1)).save(userTwo);

        assertThat(userPartialEdit).isEqualTo(userTwo);
    }

    @Test
    void addAuthorityException() {

        Exception exception = assertThrows(BadRequestException.class, () -> userService.addAuthority("truus", "ROLE_MECHANIC"));

        String expectedMessage = "Username does not exist!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void removeAuthority() {

        when(userRepository.findByUsername("peter")).thenReturn(Optional.ofNullable(userTwo));
        when(userRepository.save(userTwo)).thenReturn(userTwo);

        User authority = userService.removeAuthority("peter", "ROLE_ASSISTANT");

        verify(userRepository, times(1)).findByUsername("peter");
        verify(userRepository, times(1)).save(userTwo);

        assertThat(authority.getAuthorities()).isEqualTo(userTwo.getAuthorities());
    }

    @Test
    void removeAuthorityExceptionOne() {
        assertThrows(RecordNotFoundException.class, () -> userService.removeAuthority("truus", "ROLE_ASSISTANT"));
    }

    @Test
    void getAuthorities() {

        when(userRepository.findByUsername("florus")).thenReturn(Optional.ofNullable(userOne));

        Set<Authority> authority = userService.getAuthorities("florus");

        assertThat(authority).isEqualTo(userOne.getAuthorities());
    }

    @Test
    void getAuthoritiesException() {

        assertThrows(RecordNotFoundException.class, () -> userService.getAuthorities("truus"));
    }


    @Test
    void userPasswordChange() {
    }

    @Test
    void loggedInUsernameIsTheSameAsUsernameInput() {
    }

    @Test
    void loggedInUsernameIsAdmin() {
    }

}