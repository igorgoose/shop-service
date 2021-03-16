package by.bsu.tp.lab2.service.impl;

import by.bsu.tp.lab2.model.OrderPosition;
import by.bsu.tp.lab2.model.OrderRequest;
import by.bsu.tp.lab2.model.Product;
import by.bsu.tp.lab2.repsoitory.OrderRequestRepository;
import by.bsu.tp.lab2.repsoitory.ProductRepository;
import by.bsu.tp.lab2.service.OrderRequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@AllArgsConstructor
@Service
public class OrderRequestServiceImpl implements OrderRequestService {

    private final OrderRequestRepository orderRequestRepository;
    private final ProductRepository productRepository;

    @Override
    public List<OrderRequest> getAll() {
        return orderRequestRepository.findAll();
    }

    @Override
    public OrderRequest getById(long id) {
        return orderRequestRepository.findById(id).orElseThrow(() -> new RuntimeException("Request not found(id = " + id + ")"));
    }

    @Override
    public OrderRequest create() {
        return orderRequestRepository.save(new OrderRequest());
    }

    @Override
    public void addProduct(long requestId, long productId, int quantity) {
        OrderRequest orderRequest = orderRequestRepository.findById(requestId).
                orElseThrow(() -> new RuntimeException("Request not found(id = " + requestId + ")"));
        Product persistedProduct = productRepository.findById(productId).
                orElseThrow(() -> new RuntimeException("Product not found(id = " + productId + ")"));
        if(quantity > persistedProduct.getQuantity()) {
            throw new RuntimeException("Cannot add " + quantity + " of product " + persistedProduct.getName() +
                    ". There's not enough in stock");
        }
        OrderPosition orderPosition = orderRequest.getOrderPositions().stream()
                .filter(orderPosition1 -> orderPosition1.getProduct().getId().equals(productId))
                .findAny().orElse(null);
        if(orderPosition != null) {
            orderPosition.setQuantity(orderPosition.getQuantity() + quantity);
        } else {
            orderPosition = new OrderPosition();
            orderPosition.setOrderRequest(orderRequest);
            orderPosition.setProduct(persistedProduct);
            orderPosition.setQuantity(quantity);
            orderRequest.getOrderPositions().add(orderPosition);
        }
        orderRequest.setTotalPrice(orderRequest.getTotalPrice() + quantity * persistedProduct.getPrice());
        persistedProduct.setQuantity(persistedProduct.getQuantity() - quantity);
        productRepository.save(persistedProduct);
        orderRequestRepository.save(orderRequest);
    }
}
