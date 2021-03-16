package by.bsu.tp.lab2.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private int quantity;

    @OneToMany(mappedBy = "product")
    private List<OrderPosition> orderPositions;
}
