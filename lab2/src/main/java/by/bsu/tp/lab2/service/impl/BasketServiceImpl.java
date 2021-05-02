package by.bsu.tp.lab2.service.impl;

import by.bsu.tp.lab2.model.BasketPosition;
import by.bsu.tp.lab2.model.User;
import by.bsu.tp.lab2.repository.UserRepository;
import by.bsu.tp.lab2.service.BasketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@AllArgsConstructor
@Service
public class BasketServiceImpl implements BasketService {

    private final UserRepository userRepository;

    @Override
    public List<BasketPosition> getBasketPositionsByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid user id(" + userId + ")")
        );
        return user.getBasketPositions();
    }

}
