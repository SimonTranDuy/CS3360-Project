package backend.com.example.backendcs3360.repositories;

import backend.com.example.backendcs3360.models.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

}