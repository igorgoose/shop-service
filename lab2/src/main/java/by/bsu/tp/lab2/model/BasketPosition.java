package by.bsu.tp.lab2.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "basket_position")
public class BasketPosition {
    
    @Id
    private BasketPositionId id;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @MapsId("userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @MapsId("productId")
    private Product product;

}