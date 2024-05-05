package backend.com.example.backendcs3360.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "list_of_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "list_of_items_id")
    private int list_of_items_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "date_of_purchase")
    private Date date_of_purchase;

    public OrderItem() {
    }

    public OrderItem(Customer customer, Item item, int quantity, Date date_of_purchase) {
        this.customer = customer;
        this.item = item;
        this.quantity = quantity;
        this.date_of_purchase = date_of_purchase;
    }

    // Getters and setters...
    public int getList_of_items_id() {
        return list_of_items_id;
    }

    public void setList_of_items_id(int list_of_items_id) {
        this.list_of_items_id = list_of_items_id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate_of_purchase() {
        return date_of_purchase;
    }

    public void setDate_of_purchase(Date date_of_purchase) {
        this.date_of_purchase = date_of_purchase;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "list_of_items_id=" + list_of_items_id +
                ", customer=" + customer +
                ", item=" + item +
                ", quantity=" + quantity +
                ", date_of_purchase=" + date_of_purchase +
                '}';
    }
}