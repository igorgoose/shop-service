package by.bsu.tp.lab2.util;

import by.bsu.tp.lab2.model.Product;
import by.bsu.tp.lab2.model.dto.ProductDto;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ProductDtoConverter {

    public static ProductDto.Response.Short convert(Product product) {
        return new ProductDto.Response.Short(product);
    }

    public static List<ProductDto.Response.Short> convert(List<Product> products) {
        return products.stream().map(ProductDto.Response.Short::new).collect(Collectors.toList());
    }
}
