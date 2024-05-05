package backend.com.example.backendcs3360.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.com.example.backendcs3360.models.Cart;

public interface CartReporitory extends JpaRepository<Cart, Integer> {
    
}
