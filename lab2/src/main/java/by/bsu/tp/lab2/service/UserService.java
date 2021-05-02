package by.bsu.tp.lab2.service;

import by.bsu.tp.lab2.model.User;
import by.bsu.tp.lab2.model.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User getUserById(long id);
    User registerUser(UserDto.Request.SignUp user);
    User findByUsername(String username);
}
