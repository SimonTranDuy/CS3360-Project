package backend.com.example.backendcs3360.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.com.example.backendcs3360.models.Accessories;

public interface AccessoriesReporitory extends JpaRepository<Accessories, Integer> {
    
}
