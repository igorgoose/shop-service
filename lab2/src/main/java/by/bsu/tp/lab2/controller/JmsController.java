package by.bsu.tp.lab2.controller;

import by.bsu.tp.lab2.jms.JmsComponent;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jms")
public class JmsController {

    private final JmsComponent jmsComponent;

    public JmsController(JmsComponent jmsComponent) {
        this.jmsComponent = jmsComponent;
    }

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String send(@RequestParam String message) {
        jmsComponent.sendMessage(message);
        return "ok";
    }
}
