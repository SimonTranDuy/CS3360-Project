package backend.com.example.backendcs3360.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
// To assign name of database table corresponds to class Customer
@Table(name = "customers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CustomerDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto increment
    @Column(name = "customer_id") // To asign name of colume in database table corresponds to customer_id field
    private int customerId;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;
    @Column(name = "address")
    private String address;

    @OneToMany(fetch = FetchType.EAGER)
    private List<OrderItemDTO> orderItems;

    public CustomerDTO() {
    }

    public CustomerDTO(int customerId, String customerName, String phoneNumber, String address) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "customer_id=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public CustomerDTO convertToCustomer(){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(this.customerId);
        customerDTO.setCustomerName(this.getCustomerName());
        customerDTO.setAddress(this.getAddress());
        customerDTO.setPhoneNumber(this.getPhoneNumber());
        return customerDTO;
    }
}
