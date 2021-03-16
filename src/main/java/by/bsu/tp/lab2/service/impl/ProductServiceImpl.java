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
}
