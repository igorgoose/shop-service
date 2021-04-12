package by.bsu.tp.lab2.accountant.model;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrderRequest {

    private Long id;
    private double totalPrice;
    private String orderStatus;
    private byte[] bill;
    private Timestamp creationDate;
    private Employee author;

    private String customerName;
    private String customerAddress;
    private List<OrderPosition> orderPositions;

    public List<Product> getProducts() {
        return orderPositions.stream().map(OrderPosition::getProduct).collect(Collectors.toList());
    }
}
