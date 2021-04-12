package by.bsu.tp.lab2.accountant.model;

import lombok.Data;

@Data
public class OrderPosition {

    private Long id;
    private int quantity;
    private double pricePerProduct;
    private Product product;
    private OrderRequest orderRequest;
}
