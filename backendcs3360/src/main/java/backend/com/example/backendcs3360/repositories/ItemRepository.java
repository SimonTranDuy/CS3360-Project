package backend.com.example.backendcs3360.repositories;

import backend.com.example.backendcs3360.models.Item;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}