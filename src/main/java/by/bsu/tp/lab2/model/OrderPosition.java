package by.bsu.tp.lab2.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "order_positions")
public class OrderPosition {

    @Id
    @GeneratedValue
    private Long id;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "order_request_id")
    private OrderRequest orderRequest;
}
