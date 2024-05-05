package backend.com.example.backendcs3360.models;

import jakarta.persistence.*;

@Entity
// To assign name of database table corresponds to class Customer
@Table(name = "customers")
public class Customer 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto increment
    @Column(name = "customer_id") // To asign name of colume in database table corresponds to customer_id field  
    private int customer_id;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;
    @Column(name = "address")
    private String address;
    
    // Default constructor
    public Customer(){
    }
    // Constructor with parameters
    public Customer(int customer_id, String customerName, String phoneNumber, String address)
    {
        this.customer_id = customer_id;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    // Getter and setter
    public int getCustomer_id()
    {
        return customer_id;
    }

    public void setCustomer_id(int customer_id)
    {
        this.customer_id = customer_id;
    }

    public String getCustomer_name()
    {
        return customerName;
    }

    public void setCustomer_name(String customerName)
    {
        this.customerName = customerName;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    
    @Override
    public String toString()
    {
        return "Customer{" + 
        "customer_id=" + customer_id + 
        ", customerName='" + customerName + '\'' + 
        ", phoneNumber='" + phoneNumber + '\'' + 
        "}";
    }
}
