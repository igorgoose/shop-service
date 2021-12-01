package by.bsu.tp.lab2.service.impl;

import by.bsu.tp.lab2.model.User;
import by.bsu.tp.lab2.model.UserRole;
import by.bsu.tp.lab2.model.dto.UserDto;
import by.bsu.tp.lab2.repository.UserRepository;
import by.bsu.tp.lab2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;

import javax.annotation.PostConstruct;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    private void initUsers() {
        User admin = new User();
        admin.setId(1L);
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setFirstName("John");
        admin.setLastName("Doe");
        admin.setRole(UserRole.ADMIN.name());
        registerInitialUser(admin);
        User salesman = new User();
        salesman.setId(2L);
        salesman.setUsername("salesman");
        salesman.setPassword("salesman");
        salesman.setFirstName("Dohn");
        salesman.setLastName("Joe");
        salesman.setRole(UserRole.SALESMAN.name());
        registerInitialUser(salesman);
        User clerk = new User();
        clerk.setId(3L);
        clerk.setUsername("clerk");
        clerk.setPassword("clerk");
        clerk.setFirstName("Jodn");
        clerk.setLastName("Hoe");
        clerk.setRole(UserRole.CLERK.name());
        registerInitialUser(clerk);
        User user = new User();
        user.setId(4L);
        user.setUsername("angelina");
        user.setPassword("1488");
        user.setFirstName("Angelina");
        user.setLastName("Maximovna");
        user.setRole(UserRole.CLIENT.name());
        registerInitialUser(user);
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found(id=" + id + ")"));
    }

    private void registerInitialUser(User user) {
        if (!userRepository.existsByUsername(user.getUsername())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
    }

    @Override
    public User registerUser(UserDto.Request.SignUp userDto) {
        validateNewUser(userDto);
        User user = userDto.convert();
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "User '" + userDto.getUsername() + "' already exists.");
        }
        user.setRole(UserRole.CLIENT.name());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                             .orElseThrow(() -> new UsernameNotFoundException("User " + username + "not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return convertToUserDetails(userRepository.findByUsername(s)
                                                  .orElseThrow(() -> new UsernameNotFoundException("User " + s + "not found"))
        );
    }

    private org.springframework.security.core.userdetails.User convertToUserDetails(User user) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                UserRole.valueOf(user.getRole()).getAuthorities());
    }

    private void validateNewUser(UserDto.Request.SignUp user) {
        if (user.getUsername() == null || user.getUsername().equals("")) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Username must not be empty!");
        }
        if (user.getPassword() == null || user.getPassword().equals("")) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Password must not be empty!");
        }
        if (!user.getPassword().equals(user.getPasswordControl())) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Password and password control fields must match!");
        }
        if (user.getFirstName() == null || user.getFirstName().equals("") || user.getLastName() == null || user.getLastName().equals("")) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "First and last names must not be empty!");
        }
    }
}
