package by.bsu.tp.lab2.repsoitory;

import by.bsu.tp.lab2.model.OrderPosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderPositionRepository extends JpaRepository<OrderPosition, Long> {
}
