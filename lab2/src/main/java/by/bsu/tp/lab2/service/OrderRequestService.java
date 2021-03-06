package by.bsu.tp.lab2.service;

import by.bsu.tp.lab2.model.OrderRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.List;

public interface OrderRequestService {
    List<OrderRequest> getAll();
    List<OrderRequest> getOrders();
    List<OrderRequest> getOrdersByStatus(String status);
    OrderRequest getById(long id);
    OrderRequest create();
    void addProduct(long requestId, long productId, int quantity);
    void removeProduct(long requestId, long productId, int quantity);
    OrderRequest update(long id, OrderRequest orderRequest);
    OrderRequest updateStatus(long id, String status);
    OrderRequest issueBill(long id) throws IOException;
    byte[] getBillByOrderId(long id);
}
