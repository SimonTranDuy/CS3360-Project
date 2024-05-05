package backend.com.example.backendcs3360.repositories;

import backend.com.example.backendcs3360.models.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}