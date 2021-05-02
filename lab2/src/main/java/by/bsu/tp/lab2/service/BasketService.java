package by.bsu.tp.lab2.service;

import by.bsu.tp.lab2.model.BasketPosition;

import java.util.List;

public interface BasketService {
    List<BasketPosition> getBasketPositionsByUserId(Long userId);
}
