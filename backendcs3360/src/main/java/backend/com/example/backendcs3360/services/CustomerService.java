package backend.com.example.backendcs3360.services;

import backend.com.example.backendcs3360.exceptions.CustomerNotFoundException;
import backend.com.example.backendcs3360.exceptions.InvalidDataException;
import backend.com.example.backendcs3360.exceptions.PhoneNumberExistsException;
import backend.com.example.backendcs3360.dto.CustomerDTO;
import backend.com.example.backendcs3360.repositories.CustomerRepository;
// import backend.com.example.backendcs3360.models.Customer;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
// import java.util.stream.Collector;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository repository) {
        this.customerRepository = repository;
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(CustomerDTO::convertToCustomer)
                .collect(Collectors.toList());
    }

    public Optional<CustomerDTO> findById(Integer customerId) {
        return customerRepository.findById(customerId).map(CustomerDTO::convertToCustomer);
    }

    public List<CustomerDTO> findByCustomerName(String customerName) {
        // Convert search name into lowercase
        String searchName = customerName.trim().toLowerCase();

        List<CustomerDTO> customers = customerRepository.findByCustomerNameContainingIgnoreCase(searchName).stream()
                .map(CustomerDTO::convertToCustomer)
                .collect(Collectors.toList());
        return customers;
    }

    public CustomerDTO findByPhoneNumber(String phoneNumber) {
        CustomerDTO customer = customerRepository.findByPhoneNumber(phoneNumber.trim());
        return customer != null ? customer.convertToCustomer() : null;
    }

    public List<CustomerDTO> findByAddress(String address) {
        String searchAddress = address.trim().toLowerCase();

        List<CustomerDTO> customers = customerRepository.findByAddressContainingIgnoreCase(searchAddress).stream()
                .map(CustomerDTO::convertToCustomer)
                .collect(Collectors.toList());
        return customers;
    }

    public CustomerDTO insertCustomer(CustomerDTO newCustomerDTO) {
        // To check name must not be empty
        if (newCustomerDTO.getCustomerName() == null || newCustomerDTO.getCustomerName().isEmpty()) {
            throw new InvalidDataException("Name cannot be empty");
        }

        // To check phone number must not be empty
        if (newCustomerDTO.getPhoneNumber() == null || newCustomerDTO.getPhoneNumber().isEmpty()) {
            throw new InvalidDataException("Phone number cannot be empty");
        }

        // To check if phone number existed or not
        if (isPhoneNumberExists(newCustomerDTO.getPhoneNumber())) {
            throw new PhoneNumberExistsException("Phone number already existed");
        }

        // If there are no errors, proceed to add a new customer
        CustomerDTO newCustomer = newCustomerDTO.convertToCustomer();
        return customerRepository.save(newCustomer);
        // CustomerDTO savedCustomer = customerRepository.save(newCustomerDTO);
        // return savedCustomer;
    }

    public boolean isPhoneNumberExists(String phoneNumber) {
        return customerRepository.existsByPhoneNumber(phoneNumber);
    }

    public CustomerDTO updateOrInsertCustomer(CustomerDTO newCustomerDTO, Integer customerId) {
        CustomerDTO newCustomer = newCustomerDTO.convertToCustomer();
        newCustomer.setCustomerId(customerId);

        Optional<CustomerDTO> existingCustomerOptional = customerRepository.findById(customerId);
        if (existingCustomerOptional.isPresent()) {
            CustomerDTO existingCustomer = existingCustomerOptional.get();
            existingCustomer.setCustomerName(newCustomerDTO.getCustomerName());
            existingCustomer.setPhoneNumber(newCustomerDTO.getPhoneNumber());
            existingCustomer.setAddress(newCustomerDTO.getAddress());
            return customerRepository.save(existingCustomer);
        } else {
            // Throw exception when cannot find customer
            throw new CustomerNotFoundException("Customer not found with id: " + customerId);
        }
    }

    public boolean deleteCustomer(Integer customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new CustomerNotFoundException("Customer not found with id: " + customerId);
        }
        customerRepository.deleteById(customerId);
        return true;
    }    
}