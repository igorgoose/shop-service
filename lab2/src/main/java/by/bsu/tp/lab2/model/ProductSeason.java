package by.bsu.tp.lab2.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
@Entity(name = "product_seasons")
public class ProductSeason {

    @Id
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "productSeasons")
    private List<Product> products;

}
