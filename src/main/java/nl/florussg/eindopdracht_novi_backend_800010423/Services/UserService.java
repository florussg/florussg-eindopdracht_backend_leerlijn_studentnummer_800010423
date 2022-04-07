package nl.florussg.eindopdracht_novi_backend_800010423.Services;

import nl.florussg.eindopdracht_novi_backend_800010423.Dto.UserDto;
import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.BadRequestException;
import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.RecordNotFoundException;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Authority;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.User;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }


    public User getUser(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User userFound = optionalUser.get();;
            return userFound;
        } else {
            throw new RecordNotFoundException("User does not exist!");
        }
    }

    public String addNewUser (UserDto userDto) {

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


//                newUser.setUsername(userDto.getUsername());
//                newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
//                newUser.isEnabled(); //TODO: Is dit nodig?
//
//                //newUser.getAuthorities().clear();
//                newUser.addAuthority(new Authority(userDto.getUsername(), "ROLE_USER"));
//
//                var banaan = newUser.getId();
//
//                userRepository.save(newUser);
//                return newUser.getId();

            } catch (Exception ex) {
                throw new BadRequestException("Error in creating user!");
                }

    }



    public void addAuthority(String username, String authority) {

        if (!userRepository.findByUsername(username).isPresent()) throw new BadRequestException("Username already exists!");

            User saveUser = new User();
            saveUser.addAuthority(authority);
            userRepository.save(saveUser);
        }
}









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






