package backend.com.example.backendcs3360.repositories;

import backend.com.example.backendcs3360.dto.CustomerDTO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerDTO, Integer> {
    List<CustomerDTO> findByCustomerNameContaining (String customerName);
    List<CustomerDTO> findByCustomerNameContainingIgnoreCase(String customerName);
    List<CustomerDTO> findByAddressContaining (String address);
    List<CustomerDTO> findByAddressContainingIgnoreCase (String address);
    CustomerDTO findByPhoneNumberContaining(String phoneNumber);
    CustomerDTO findByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumber(String phoneNumber);
}