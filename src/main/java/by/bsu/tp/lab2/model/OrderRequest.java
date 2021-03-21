package by.bsu.tp.lab2.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity(name = "order_requests")
public class OrderRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double totalPrice;
    private boolean accepted;
    private boolean sent;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Employee author;

    private String customerName;
    private String customerAddress;

    @OneToMany(mappedBy = "orderRequest", cascade = CascadeType.ALL)
    private List<OrderPosition> orderPositions;

    public List<Product> getProducts() {
        return orderPositions.stream().map(OrderPosition::getProduct).collect(Collectors.toList());
    }
}
