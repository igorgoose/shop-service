package by.bsu.tp.lab2.service.impl;

import by.bsu.tp.lab2.model.Product;
import by.bsu.tp.lab2.repsoitory.ProductRepository;
import by.bsu.tp.lab2.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product(id=" + id + ") not found"));
    }

    @Override
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void updateProduct(long id, Product product) {
        Product persistedProduct = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product(id=" + id + ") not found"));
        persistedProduct.setName(product.getName());
        persistedProduct.setPrice(product.getPrice());
        productRepository.save(persistedProduct);
    }
}
