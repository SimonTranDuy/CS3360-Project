package backend.com.example.backendcs3360.repositories;

import backend.com.example.backendcs3360.dto.OrderItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListOfItemsRepository extends JpaRepository<OrderItemDTO, Integer> {
}
