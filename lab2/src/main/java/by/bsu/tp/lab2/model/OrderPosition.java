package by.bsu.tp.lab2.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "order_positions")
public class OrderPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private double pricePerProduct;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "order_request_id")
    private OrderRequest orderRequest;
}
