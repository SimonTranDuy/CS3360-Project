package backend.com.example.backendcs3360.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.com.example.backendcs3360.dto.CustomerDTO;
import backend.com.example.backendcs3360.models.Customer;
import backend.com.example.backendcs3360.models.ResponseObject;
import backend.com.example.backendcs3360.services.CustomerService;
// import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(path = "api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseObject> getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        if (!customers.isEmpty())
        {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("success", "Query all customers successfully", customers));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("success", "No customers found", customers));
        }
    }

    @GetMapping("/get-by-phone/{phoneNumber}")
    public ResponseEntity<ResponseObject> getCustomerByPhoneNumber(@PathVariable String phoneNumber) {
        Optional<CustomerDTO> customer = customerService.findByPhoneNumber(phoneNumber);
        if(!customer.isEmpty()){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("success", "Customer found by phone number: " + phoneNumber, customer.orElse(null)));
        }
        else{
            return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("error", "No customer found by phone number: " + phoneNumber, customer.orElse(null)));
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<ResponseObject> insertCustomer(@RequestBody Customer newCustomer) {
        Object result = customerService.insertCustomer(newCustomer);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("success", "Customer added successfully", result));
    }

    @PutMapping("/update/{customerId}")
    public ResponseEntity<ResponseObject> updateOrInsertCustomer(@RequestBody Customer customer, @PathVariable Integer customerId) {
        CustomerDTO updatedCustomer = customerService.updateOrInsertCustomer(customer, customerId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("success", "Customer updated/inserted successfully", updatedCustomer));
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<ResponseObject> deleteCustomer(@PathVariable Integer customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("success", "Customer deleted successfully", null));
    }
}