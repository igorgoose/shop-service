package by.bsu.tp.lab2.service;

import by.bsu.tp.lab2.model.OrderRequest;

import java.util.List;

public interface OrderRequestService {
    List<OrderRequest> getAll();
    OrderRequest getById(long id);
    OrderRequest create();
    void addProduct(long requestId, long productId, int quantity);
}
