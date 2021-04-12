package by.bsu.tp.lab2.accountant.model;

import lombok.Data;

import java.util.List;

@Data
public class Product {

    private Long id;
    private String name;
    private double price;
    private int quantity;

    private List<OrderPosition> orderPositions;

    private List<ProductSeason> productSeasons;
}
