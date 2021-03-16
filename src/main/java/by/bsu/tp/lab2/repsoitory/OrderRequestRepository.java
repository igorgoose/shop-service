package by.bsu.tp.lab2.repsoitory;

import by.bsu.tp.lab2.model.OrderRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRequestRepository extends JpaRepository<OrderRequest, Long> {
}
