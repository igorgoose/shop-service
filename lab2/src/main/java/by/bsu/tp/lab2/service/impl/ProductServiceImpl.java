package by.bsu.tp.lab2.service.impl;

import by.bsu.tp.lab2.model.Product;
import by.bsu.tp.lab2.model.ProductSeason;
import by.bsu.tp.lab2.repository.ProductRepository;
import by.bsu.tp.lab2.repository.ProductSeasonRepository;
import by.bsu.tp.lab2.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductSeasonRepository productSeasonRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProducts(String[] seasons) {
        if (seasons == null || Arrays.asList(seasons).contains("all")) {
            return getAllProducts();
        }
        return productRepository.findAll().stream().filter(product -> {
            for (String seasonName : seasons) {
                if(product.getProductSeasons().stream().map(ProductSeason::getName).anyMatch(seasonName::equals)){
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());
    }

    @Override
    public Product getById(long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product(id=" + id + ") not found"));
    }

    @Override
    public void createProduct(Product product, String[] seasons) {
        product.setProductSeasons(convertToProductSeasons(seasons));
        productRepository.save(product);
    }

    @Transactional
    @Override
    public void updateProduct(long id, Product product, String[] seasons) {
        Product persistedProduct = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product(id=" + id + ") not found"));
        persistedProduct.setName(product.getName());
        persistedProduct.setPrice(product.getPrice());
        persistedProduct.setProductSeasons(convertToProductSeasons(seasons));
        productRepository.save(persistedProduct);
    }

    private List<ProductSeason> convertToProductSeasons(String[] seasons) {
        List<ProductSeason> productSeasons = new LinkedList<>();
        for (String season : seasons) {
            Optional<ProductSeason> productSeasonOptional = productSeasonRepository.findByName(season);
            productSeasonOptional.ifPresent(productSeasons::add);
        }
        return productSeasons;
    }
}
