package by.bsu.tp.lab2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class HomeController {

    @GetMapping(value = {"", "/home"})
    public String index() {
        return "index";
    }

}
