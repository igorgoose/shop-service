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
