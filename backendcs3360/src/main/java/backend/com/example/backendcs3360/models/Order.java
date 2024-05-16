package backend.com.example.backendcs3360.models;

import java.util.Date;
import java.util.List;

//@Entity
//@Table(name = "order")
public class Order {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "order_id")
    private int orderId;
//    @Column(name = "total")
    private double total;
//    @Column(name = "phone_number")
    private String phoneNumber;
//    @Temporal(TemporalType.TIMESTAMP) // Specifies the database field as a TIMESTAMP.
//    @Column(name = "date_of_purchase")
    private Date dateOfPurchase;

//    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JsonBackReference
    private List<OrderItem> orderItems;

    public Order() {
    }

    public Order(int orderId, double total, String phoneNumber, Date dateOfPurchase, List<OrderItem> orderItems) {
        this.orderId = orderId;
        this.total = total;
        this.phoneNumber = phoneNumber;
        this.dateOfPurchase = dateOfPurchase;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
