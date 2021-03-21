package by.bsu.tp.lab2.service.impl;

import by.bsu.tp.lab2.model.OrderPosition;
import by.bsu.tp.lab2.model.OrderRequest;
import by.bsu.tp.lab2.model.Product;
import by.bsu.tp.lab2.repsoitory.EmployeeRepository;
import by.bsu.tp.lab2.repsoitory.OrderPositionRepository;
import by.bsu.tp.lab2.repsoitory.OrderRequestRepository;
import by.bsu.tp.lab2.repsoitory.ProductRepository;
import by.bsu.tp.lab2.service.OrderRequestService;
import lombok.AllArgsConstructor;
import org.hibernate.criterion.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@AllArgsConstructor
@Service
public class OrderRequestServiceImpl implements OrderRequestService {

    private final OrderRequestRepository orderRequestRepository;
    private final ProductRepository productRepository;
    private final OrderPositionRepository orderPositionRepository;
    private final EmployeeRepository employeeRepository;

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
        String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setAuthor(employeeRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Unknown user " + username))
        );
        return orderRequestRepository.save(orderRequest);
    }

    @Override
    public void addProduct(long requestId, long productId, int quantity) {
        OrderRequest orderRequest = orderRequestRepository.findById(requestId).
                orElseThrow(() -> new RuntimeException("Request not found(id = " + requestId + ")"));
        Product persistedProduct = productRepository.findById(productId).
                orElseThrow(() -> new RuntimeException("Product not found(id = " + productId + ")"));
        if (quantity > persistedProduct.getQuantity()) {
            throw new RuntimeException("Cannot add " + quantity + " of product " + persistedProduct.getName() +
                    ". There's not enough in stock");
        }
        OrderPosition orderPosition = orderRequest.getOrderPositions().stream()
                .filter(orderPosition1 -> orderPosition1.getProduct().getId().equals(productId))
                .findAny().orElse(null);
        if (orderPosition != null) {
            orderPosition.setQuantity(orderPosition.getQuantity() + quantity);
        } else {
            orderPosition = new OrderPosition();
            orderPosition.setOrderRequest(orderRequest);
            orderPosition.setPricePerProduct(persistedProduct.getPrice());
            orderPosition.setProduct(persistedProduct);
            orderPosition.setQuantity(quantity);
            orderRequest.getOrderPositions().add(orderPosition);
        }
        orderRequest.setTotalPrice(orderRequest.getTotalPrice() + quantity * orderPosition.getPricePerProduct());
        persistedProduct.setQuantity(persistedProduct.getQuantity() - quantity);
        productRepository.save(persistedProduct);
        orderRequestRepository.save(orderRequest);
    }

    @Override
    public void removeProduct(long requestId, long productId, int quantity) {
        OrderRequest orderRequest = orderRequestRepository.findById(requestId).
                orElseThrow(() -> new RuntimeException("Request not found(id = " + requestId + ")"));
        Product persistedProduct = productRepository.findById(productId).
                orElseThrow(() -> new RuntimeException("Product not found(id = " + productId + ")"));
        OrderPosition orderPosition = orderRequest.getOrderPositions().stream()
                .filter(orderPosition1 -> orderPosition1.getProduct().getId().equals(productId))
                .findAny().orElseThrow(() -> new RuntimeException("Request(id=" + requestId + ") doesn't contain product " + persistedProduct.getName()));
        if (quantity > orderPosition.getQuantity()) {
            throw new RuntimeException("Cannot add " + quantity + " of product " + persistedProduct.getName() +
                    ". There's not enough in stock");
        }
        if (quantity == orderPosition.getQuantity()) {
            orderRequest.getOrderPositions().remove(orderPosition);
            orderPositionRepository.delete(orderPosition);
        } else {
            orderPosition.setQuantity(orderPosition.getQuantity() - quantity);
            orderRequest.setTotalPrice(orderRequest.getTotalPrice() + quantity * persistedProduct.getPrice());
        }
        persistedProduct.setQuantity(persistedProduct.getQuantity() + quantity);
        productRepository.save(persistedProduct);
        orderRequestRepository.save(orderRequest);
    }

    @Override
    public OrderRequest update(long id, OrderRequest request) {
        OrderRequest persistedRequest = orderRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order request with id " + id + "not found."));
        persistedRequest.setCustomerName(request.getCustomerName());
        persistedRequest.setCustomerAddress(request.getCustomerAddress());
        return orderRequestRepository.save(persistedRequest);
    }
}
