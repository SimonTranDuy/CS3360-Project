package backend.com.example.backendcs3360.services;

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

    //!! Which one is better?
    public List<CustomerDTO> getAllCustomers() {
        // List<CustomerDTO> foundAllCustomersDTO = customerRepository.findAll();
        // List<CustomerDTO> customersDTO = foundAllCustomersDTO.stream()
        // .map(CustomerDTO::convertToCustomer)
        // .collect(Collectors.toList());
        // return customersDTO;

        return customerRepository.findAll().stream()
                .map(CustomerDTO::convertToCustomer)
                .collect(Collectors.toList());
    }

    public Optional<CustomerDTO> findById(Integer customerId) {
        return customerRepository.findById(customerId).map(CustomerDTO::convertToCustomer);
    }

    //!! Which one is better?
    public List<CustomerDTO> findByCustomerName(String customerName) {
        return customerRepository.findByCustomerNameContaining(customerName.trim()).stream()
                .map(CustomerDTO::convertToCustomer)
                .collect(Collectors.toList());

        // List<CustomerDTO> foundWithCustomersNameDTO =
        // customerRepository.findByCustomerNameContaining(customerName);
        // List<CustomerDTO> customersDTO = foundWithCustomersNameDTO.stream()
        // .map(CustomerDTO::convertToCustomer)
        // .collect(Collectors.toList());
        // return customersDTO;
    }

    public CustomerDTO findByPhoneNumber(String phoneNumber) {
        return customerRepository.findByPhoneNumberContaining(phoneNumber.trim()).convertToCustomer();
    }

    public CustomerDTO findByAddress(String address){
        return customerRepository.findByAddressContaining(address.trim()).convertToCustomer();
    }

    public CustomerDTO insertCustomer(CustomerDTO newCustomerDTO) {
        CustomerDTO newCustomer = newCustomerDTO.convertToCustomer();
        return customerRepository.save(newCustomer);
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
            newCustomerDTO.setCustomerId(customerId);
            return customerRepository.save(newCustomerDTO);
        }
    }

    public boolean deleteCustomer(Integer customerId) {
        if (!customerRepository.existsById(customerId)) {
            return false;
        }
        customerRepository.deleteById(customerId);
        return true;
    }

}