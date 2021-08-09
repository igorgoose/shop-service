package by.bsu.tp.lab2.jms;

import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@EnableJms
public class JmsListenerComponent {

    @JmsListener(destination = "destination")
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
    }
}
