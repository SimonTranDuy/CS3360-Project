package backend.com.example.backendcs3360.repositories;

import backend.com.example.backendcs3360.models.Clothes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClothesRepository extends JpaRepository<Clothes, Integer> {

}