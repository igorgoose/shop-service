package by.bsu.tp.lab2.accountant.model;

import lombok.Data;

import java.util.List;

@Data
public class ProductSeason {

    private Long id;
    private String name;

    private List<Product> products;

}
