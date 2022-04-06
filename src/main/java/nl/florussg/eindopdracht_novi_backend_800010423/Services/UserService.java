package nl.florussg.eindopdracht_novi_backend_800010423.Services;

import nl.florussg.eindopdracht_novi_backend_800010423.Models.User;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((UserDetails) authentication.getPrincipal()).getUsername();
    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(String username) {
        return userRepository.findByUsername(username);
    }

//    public String createUser(UserPostRequestDto userPostRequest) {
//        try {
//            String encryptedPassword = passwordEncoder.encode(userPostRequest.getPassword());
//
//            User user = new User();
//            user.setUsername(userPostRequest.getUsername());
//            user.setPassword(encryptedPassword);
//            user.setEmail(userPostRequest.getEmail());
//            user.setEnabled(true);
//            user.addAuthority("ROLE_USER");
//            for (String s : userPostRequest.getAuthorities()) {
//                if (!s.startsWith("ROLE_")) {
//                    s = "ROLE_" + s;
//                }
//                s = s.toUpperCase();
//                if (!s.equals("ROLE_USER")) {
//                    user.addAuthority(s);
//                }
//            }
//
//            User newUser = userRepository.save(user);
//            return newUser.getUsername();
//        } catch (Exception ex) {
//            throw new BadRequestException("Cannot create user.");
//        }
//    }
//
//    public void updateUser(String username, User user) {
//        Optional<User> optionalUser = userRepository.findById(username);
//        if (optionalUser.isEmpty()) {
//            throw new UserNotFoundException(username + " not found");
//        } else {
//            User existingUser = optionalUser.get();
//            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
//            existingUser.setEmail(user.getEmail());
//            existingUser.setEnabled(user.isEnabled());
//            userRepository.save(user);
//
//        }
//    }
//
//    public void deleteUser(String username) {
//        if (userRepository.existsById(username)) {
//            userRepository.deleteById(username);
//        } else {
//            throw new UserNotFoundException(username + " not found");
//        }
//    }
//
//    public Set<Authority> getAuthorities(String username) {
//        Optional<User> optionalUser = userRepository.findById(username);
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            return user.getAuthorities();
//        } else {
//            throw new UserNotFoundException(username + " not found");
//        }
//    }
//
//    public void addAuthority(String username, String authorityString) {
//        Optional<User> userOptional = userRepository.findById(username);
//        if (userOptional.isEmpty()) {
//            throw new UserNotFoundException(username);
//        } else {
//            User user = userOptional.get();
//            user.addAuthority(authorityString);
//            userRepository.save(user);
//        }
//    }
//
//    public void removeAuthority(String username, String authorityString) {
//        Optional<User> userOptional = userRepository.findById(username);
//        if (userOptional.isEmpty()) {
//            throw new UserNotFoundException(username);
//        } else {
//            User user = userOptional.get();
//            user.removeAuthority(authorityString);
//            userRepository.save(user);
//        }
//    }




}

