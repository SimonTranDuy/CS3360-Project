package backend.com.example.backendcs3360.repositories;

import backend.com.example.backendcs3360.dto.CustomerDTO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerDTO, Integer> {
    List<CustomerDTO> findByCustomerNameContaining (String customerName);
    CustomerDTO findByAddressContaining (String address);
    CustomerDTO findByPhoneNumberContaining(String phoneNumber);
}