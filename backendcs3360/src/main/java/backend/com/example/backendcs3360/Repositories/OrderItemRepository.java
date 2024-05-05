package backend.com.example.backendcs3360.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.com.example.backendcs3360.models.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    
}
