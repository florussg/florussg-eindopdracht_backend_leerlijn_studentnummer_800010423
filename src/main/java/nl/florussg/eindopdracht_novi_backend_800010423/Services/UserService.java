package nl.florussg.eindopdracht_novi_backend_800010423.Services;

import nl.florussg.eindopdracht_novi_backend_800010423.Dto.UserDto;
import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.BadRequestException;
import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.RecordNotFoundException;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Authority;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.User;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User userFound = optionalUser.get();

            return userFound;
        } else {
            throw new RecordNotFoundException("User does not exist!");
        }
    }

    public String addNewUser(UserDto userDto) {

        Optional<User> optionalUser = userRepository.findByUsername(userDto.getUsername());
        if (optionalUser.isPresent()) {
            throw new BadRequestException("Username already exists, please choose another username");
        }

        try {

            User user = new User();

            user.setUsername(userDto.getUsername());

            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.isEnabled();
            user.addAuthority("ROLE_USER");

            User saveUser = userRepository.save(user);

            return saveUser.getUsername();



        } catch (Exception ex) {
            throw new BadRequestException("Error in creating user!");
        }
    }


    public User addAuthority(String username, String authority) {

        if (!userRepository.findByUsername(username).isPresent())
            throw new BadRequestException("Username does not exist!");

        User saveUser = userRepository.findByUsername(username).get();
        saveUser.addAuthority(authority);
        userRepository.save(saveUser);
        return saveUser;
    }


    public User userPasswordChange(String username, String password) {
        if (loggedInUsernameIsTheSameAsUsernameInput(username) == true) {
            if (isValidPassword(password) == true) {

                User userToSetNewPassword = userRepository.findByUsername(username).get();
                userToSetNewPassword.setPassword(passwordEncoder.encode(password));
                userRepository.save(userToSetNewPassword);

                return userToSetNewPassword;

            } else {
                throw new BadRequestException(
                        "Chosen password is not valid: " +
                                "length min 8, 1 digit, 1 lowercase, " +
                                "1 highercase and 1 special character mandatory!");
                }

            } else if (loggedInUsernameIsAdmin() == true) {
            if (isValidPassword(password) == true) {

                User userToSetNewPassword = userRepository.findByUsername(username).get();
                userToSetNewPassword.setPassword(passwordEncoder.encode(password));
                userRepository.save(userToSetNewPassword);

            } else {
                throw new BadRequestException(
                        "Chosen password is not valid: " +
                                "length min 8, 1 digit, 1 lowercase, " +
                                "1 highercase and 1 special character mandatory!");
                }

            } else {
            throw new BadRequestException(
                    "You are not allowed to change the password of another user!");
        }
        return null;
    }


    public User removeAuthority(String username, String authorityString) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new RecordNotFoundException("Username does not exist!");
        } else {
            User user = userOptional.get();
            user.removeAuthority(authorityString);
            userRepository.save(user);
            return user;
        }
    }


    private String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((UserDetails) authentication.getPrincipal()).getUsername();
    }

    public Set<Authority> getAuthorities(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new RecordNotFoundException("User not found");
        }
        else {
            User user = userOptional.get();
            return user.getAuthorities();
        }
    }

    public boolean loggedInUsernameIsTheSameAsUsernameInput(String username) {
        if (username.equals(getCurrentUserName())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean loggedInUsernameIsAdmin() {
        var currentUser = getCurrentUserName();
        var check = getAuthorities(currentUser);
        ArrayList<String> list = new ArrayList<>();

        for (Authority a : check) {
            String admin = a.getAuthority();
            if (admin.equals("ROLE_ADMIN")) {
                list.add("Admin found!");
            }
        }
        if (list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isValidPassword(String password) {
        final int MIN_LENGTH = 8;
        final int MIN_DIGITS = 1;
        final int MIN_LOWER = 1;
        final int MIN_UPPER = 1;
        final int MIN_SPECIAL = 1;
        final String SPECIAL_CHARS = "@#$%&*!()+=-_";

        long countDigit = password.chars().filter(ch -> ch >= '0' && ch <= '9').count();
        long countLower = password.chars().filter(ch -> ch >= 'a' && ch <= 'z').count();
        long countUpper = password.chars().filter(ch -> ch >= 'A' && ch <= 'Z').count();
        long countSpecial = password.chars().filter(ch -> SPECIAL_CHARS.indexOf(ch) >= 0).count();

        boolean validPassword = password.length() >= MIN_LENGTH;
        if (countLower < MIN_LOWER)
            validPassword = false;
        if (countUpper < MIN_UPPER)
            validPassword = false;
        if (countDigit < MIN_DIGITS)
            validPassword = false;
        if (countSpecial < MIN_SPECIAL)
            validPassword = false;

        return validPassword;
    }

}



















