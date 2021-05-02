package by.bsu.tp.lab2.controller;

import by.bsu.tp.lab2.model.dto.UserDto;
import by.bsu.tp.lab2.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signUp(@ModelAttribute("user") UserDto.Request.SignUp user) {
        return "signup";
    }

    @PostMapping("/signup")
    public String registerUser(@ModelAttribute("user") UserDto.Request.SignUp userDto) {
        userService.registerUser(userDto);
        return "login";
    }
}
