package by.bsu.tp.lab2.controller;

import by.bsu.tp.lab2.util.AuthenticationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping()
public class HomeController {

    private final AuthenticationUtil authenticationUtil;

    @GetMapping(value = {"", "/home"})
    public String index(Model model) {
        authenticationUtil.injectEmployee(model);
        return "index";
    }

}
