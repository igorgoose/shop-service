package by.bsu.tp.lab2.repository;

import by.bsu.tp.lab2.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
