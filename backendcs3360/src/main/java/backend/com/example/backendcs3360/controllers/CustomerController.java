package backend.com.example.backendcs3360.controllers;

import java.util.List;

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
import backend.com.example.backendcs3360.exceptions.CustomerNotFoundException;
import backend.com.example.backendcs3360.exceptions.InvalidDataException;
import backend.com.example.backendcs3360.exceptions.PhoneNumberExistsException;
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
        ResponseObject responseObject = new ResponseObject("success", "Query all customers successfully", customers);
        return ResponseEntity.status(HttpStatus.OK).body(responseObject);
    }

    @GetMapping("/get-by-id/{customerId}")
    public ResponseEntity<ResponseObject> getCustomerById(@PathVariable Integer customerId) {
        return customerService.findById(customerId)
                .map(customer -> ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject("Success", "Query customer by ID successfully", customer)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseObject("error", "Customer not found with ID: " + customerId, null)));
    }

    @GetMapping("/get-by-name/{customerName}")
    public ResponseEntity<ResponseObject> getCustomerByname(@PathVariable String customerName) {
        List<CustomerDTO> customers = customerService.findByCustomerName(customerName);
        if (!customers.isEmpty()) {
            ResponseObject responseObject = new ResponseObject("success", "Query customer by name successfully",
                    customers);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        } else {
            ResponseObject responseObject = new ResponseObject("error", "No customer found with name: " + customerName,
                    null);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        }
    }

    @GetMapping("/get-by-phone/{phoneNumber}")
    public ResponseEntity<ResponseObject> getCustomerByPhone(@PathVariable String phoneNumber) {
        CustomerDTO customer = customerService.findByPhoneNumber(phoneNumber);
        if (customer != null) {
            ResponseObject responseObject = new ResponseObject("success", "Query customer by phone successfully",
                    customer);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        } else {
            ResponseObject responseObject = new ResponseObject("error",
                    "No customer found with phone number: " + phoneNumber, null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObject);
        }
    }

    @GetMapping("/get-by-address/{address}")
    public ResponseEntity<ResponseObject> getCustomerByAddress(@PathVariable String address) {
        List<CustomerDTO> customer = customerService.findByAddress(address);
        if (customer != null) {
            ResponseObject responseObject = new ResponseObject("success", "Query customer by address successfully",
                    customer);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        } else {
            ResponseObject responseObject = new ResponseObject("error", "No customer found with address: " + address,
                    null);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<ResponseObject> insertCustomer(@RequestBody CustomerDTO newCustomerDTO) {
        try {
            CustomerDTO savedCustomer = customerService.insertCustomer(newCustomerDTO);
            ResponseObject responseObject = new ResponseObject("success", "Insert customer successfully",
                    savedCustomer);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseObject);
        } catch (InvalidDataException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject("error", e.getMessage(), null));
        }

        catch (PhoneNumberExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject("error", e.getMessage(), null));
        }

        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("error", e.getMessage(), null));
        }

    }

    @PutMapping("/update/{customerId}")
    public ResponseEntity<ResponseObject> updateCustomer(@RequestBody CustomerDTO newCustomerDTO,
            @PathVariable Integer customerId) {
        // Check if request body contains data
        if (newCustomerDTO == null) {
            ResponseObject responseObject = new ResponseObject("error", "Request body is empty", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }

        // Attempt to update or insert the customer
        try {
            CustomerDTO updatedCustomer = customerService.updateOrInsertCustomer(newCustomerDTO, customerId);
            ResponseObject responseObject = new ResponseObject("success", "Update customer successfully",
                    updatedCustomer);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        } catch (CustomerNotFoundException ex) {
            ResponseObject responseObject = new ResponseObject("error", ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObject);
        } catch (Exception ex) {
            ResponseObject responseObject = new ResponseObject("error", "Failed to update customer", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<ResponseObject> deleteCustomer(@PathVariable Integer customerId) {
        try {
            boolean isDeleted = customerService.deleteCustomer(customerId);
            if (isDeleted) {
                return ResponseEntity.ok().body(new ResponseObject("success", "Delete customer successfully", null));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ResponseObject("error", "Delete customer failed", null));
            }
        } catch (CustomerNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject("error", ex.getMessage(), null));
        }
    }

}
