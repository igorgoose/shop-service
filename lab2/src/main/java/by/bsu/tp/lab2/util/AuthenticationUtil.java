package by.bsu.tp.lab2.util;

import by.bsu.tp.lab2.model.User;
import by.bsu.tp.lab2.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@AllArgsConstructor
@Component
public class AuthenticationUtil {

    private final UserService employeeService;

    public void injectUser(Model model) {
        User user = employeeService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", user);
    }
}
