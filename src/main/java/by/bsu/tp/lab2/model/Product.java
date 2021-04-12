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

    @ManyToMany
    @JoinTable(
            name = "products_to_product_seasons",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "product_season_id"))
    private List<ProductSeason> productSeasons;

    public String getSeasonsString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (ProductSeason season : productSeasons) {
            stringBuilder.append(season.getName()).append(" | ");
        }
        if (stringBuilder.length() != 0) {
            stringBuilder.delete(stringBuilder.length() - 3, stringBuilder.length());
        }
        return stringBuilder.toString();
    }
}
