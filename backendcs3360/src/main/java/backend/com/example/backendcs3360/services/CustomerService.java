package backend.com.example.backendcs3360.services;

import backend.com.example.backendcs3360.dto.CustomerDTO;
import backend.com.example.backendcs3360.models.Customer;
import backend.com.example.backendcs3360.repositories.CustomerRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository repository) {
        this.customerRepository = repository;
    }

    // Method getAllCustomers
    public List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> customers = customerRepository.findAll();
        // if (customers.isEmpty()) {
        //     throw new RuntimeException("No customers found.");
        // }
        return customers;
    }

    // Method findByPhoneNumber
    public Optional<CustomerDTO> findByPhoneNumber(String phoneNumber) {
        return customerRepository.findByPhoneNumber(phoneNumber);
    }

// Method insertCustomer
public CustomerDTO insertCustomer(Customer newCustomer) {
    if (newCustomer.getCustomerName() == null || newCustomer.getCustomerName().isEmpty()) {
        throw new RuntimeException("Name cannot be empty");
    }

    if (newCustomer.getPhoneNumber() == null || newCustomer.getPhoneNumber().isEmpty()) {
        throw new RuntimeException("Phone number cannot be empty");
    }

    if (newCustomer.getPhoneNumber().length() != 10) {
        throw new RuntimeException("Wrong format! Phone number must have 10 numbers");
    }

    Optional<CustomerDTO> existingCustomerDTO = customerRepository.findByPhoneNumber(newCustomer.getPhoneNumber());
    if (existingCustomerDTO.isPresent()) {
        // Return existing customer if phone number already exists
        return existingCustomerDTO.get();
    } else {
        // Save new customer if phone number doesn't exist
        CustomerDTO newDTO = newCustomer.convertToDTO();
        return customerRepository.save(newDTO);
    }
}


    // Method updateOrInsertCustomer
    public CustomerDTO updateOrInsertCustomer(Customer customer, Integer customerId) {
        if (customerId == null) {
            throw new RuntimeException("Customer ID cannot be null");
        }

        Optional<CustomerDTO> existingCustomerOptional = customerRepository.findById(customerId);
        if (existingCustomerOptional.isPresent()) {
            // Cập nhật thông tin nếu khách hàng đã tồn tại
            CustomerDTO existingCustomer = existingCustomerOptional.get();
            existingCustomer.setCustomerName(customer.getCustomerName());
            existingCustomer.setPhoneNumber(customer.getPhoneNumber());
            existingCustomer.setAddress(customer.getAddress());
            return customerRepository.save(existingCustomer);
        } else {
            // Tạo một khách hàng mới nếu không tìm thấy khách hàng với customerId
            CustomerDTO newCustomer = customer.convertToDTO();
            newCustomer.setCustomerId(customerId);
            return customerRepository.save(newCustomer);
        }
    }

// Method deleteCustomer
public void deleteCustomer(Integer customerId) {
    if (!customerRepository.existsById(customerId)) {
        throw new RuntimeException("Customer not found with id: " + customerId);
    }
    customerRepository.deleteById(customerId);
}

}
