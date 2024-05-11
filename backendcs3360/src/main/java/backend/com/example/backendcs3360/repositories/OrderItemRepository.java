package backend.com.example.backendcs3360.repositories;

import backend.com.example.backendcs3360.dto.OrderItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItemDTO, Integer> {
    List<OrderItemDTO> findByCustomer_CustomerIdAndDateOfPurchaseIsNull(int CustomerId);
    OrderItemDTO findByCustomer_CustomerIdAndItem_ItemIdAndDateOfPurchaseIsNull(int CustomerId, int ItemId);
    List<OrderItemDTO> findByCustomer_CustomerIdAndDateOfPurchaseIsNotNullOrderByDateOfPurchaseDesc(int CustomerId);
    List<OrderItemDTO> findByCustomer_CustomerIdAndDateOfPurchaseIsNotNullOrderByDateOfPurchaseAsc(int CustomerId);
}
