package by.bsu.tp.lab2.util;

import by.bsu.tp.lab2.model.Employee;
import by.bsu.tp.lab2.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@AllArgsConstructor
@Component
public class AuthenticationUtil {

    private final EmployeeService employeeService;

    public void injectEmployee(Model model) {
        Employee user = employeeService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", user);
    }
}
