package by.bsu.tp.lab2.model.dto;

import by.bsu.tp.lab2.model.Product;
import by.bsu.tp.lab2.model.ProductSeason;
import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private double price;
    private int quantity;
    private String seasons;

    public ProductDto(Product product) {
        id = product.getId();
        name = product.getName();
        price = product.getPrice();
        quantity = product.getQuantity();
        StringBuilder stringBuilder = new StringBuilder();
        for (ProductSeason season : product.getProductSeasons()) {
            stringBuilder.append(season.getName()).append(" | ");
        }
        if (!stringBuilder.isEmpty()) {
            stringBuilder.delete(stringBuilder.length() - 3, stringBuilder.length());
        }
        seasons = stringBuilder.toString();
    }
}
