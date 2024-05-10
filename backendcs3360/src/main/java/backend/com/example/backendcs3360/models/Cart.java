package backend.com.example.backendcs3360.models;

import java.util.List;
import java.util.Date;


public class Cart {
    private int cartId;
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
    
    public Cart() {
    }

    public Cart(double total, String phoneNumber, Date dateOfPurchase) {
        this.total = total;
        this.phoneNumber = phoneNumber;
        this.dateOfPurchase = dateOfPurchase;
    }

    public int getCardId() {
        return cartId;
    }

    public void setCardId(int cartId) {
        this.cartId = cartId;
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

    public void setDateOfPurchase(Date date_of_purchase) {
        this.dateOfPurchase = date_of_purchase;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", orderItems=" + orderItems +
                ", total=" + total +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfPurchase=" + dateOfPurchase +
                '}';
    }
}
