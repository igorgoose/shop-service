package by.bsu.tp.lab2.service;

import by.bsu.tp.lab2.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    List<Product> getAllProducts(String[] seasons);

    Product getById(long id);
    void createProduct(Product product, String[] seasons);
    void updateProduct(long id, Product product, String[] seasons);
}
