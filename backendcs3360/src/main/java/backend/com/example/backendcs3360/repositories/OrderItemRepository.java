package backend.com.example.backendcs3360.repositories;

import backend.com.example.backendcs3360.dto.OrderItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItemDTO, Integer> {
    List<OrderItemDTO> findByCustomerIdAndDateOfPurchaseIsNull(int customerId);
    List<OrderItemDTO> findByCustomerIdAndDateOfPurchaseIsNotNullOrderByDateOfPurchaseDesc(int customerId);
    List<OrderItemDTO> findByCustomerIdAndDateOfPurchaseIsNotNullOrderByDateOfPurchaseAsc(int customerId);
}
