package by.bsu.tp.lab2.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "product_seasons")
public class ProductSeason {

    @Id
    private Long id;
    @Column(unique = true)
    private String name;

}
