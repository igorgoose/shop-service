package by.bsu.tp.lab2.repository;

import by.bsu.tp.lab2.model.ProductSeason;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductSeasonRepository extends JpaRepository<ProductSeason, Long> {
    Optional<ProductSeason> findByName(String name);
}
