package by.bsu.tp.lab2.repository;

import by.bsu.tp.lab2.model.OrderRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRequestRepository extends JpaRepository<OrderRequest, Long> {
    List<OrderRequest> findAllByOrderStatusIsNot(String orderStatus);
    List<OrderRequest> findAllByOrderStatusIn(List<String> orderStatuses);
    List<OrderRequest> findAllByOrderStatus(String orderStatus);
}
