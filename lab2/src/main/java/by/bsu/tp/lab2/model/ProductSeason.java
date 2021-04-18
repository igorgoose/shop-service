package by.bsu.tp.lab2.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "product_seasons")
public class ProductSeason {

    @Id
    private Long id;
    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "productSeasons")
    private List<Product> products;

}
