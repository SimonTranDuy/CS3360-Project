package backend.com.example.backendcs3360.models;

import jakarta.persistence.*;

@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private int orderItemId;
    @ManyToOne(fetch = FetchType.LAZY) // To optimize performance and avoid unneccessary data downloads
    @JoinColumn(name = "item_id")
    private Item item;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "price")
    private double price;
    @Column(name = "order_id")
    private int orderId;

    public OrderItem() {
    }

    public OrderItem(Item item, int quantity, double price, int orderId) {
        this.item = item;
        this.quantity = quantity;
        this.price = price;
        this.orderId = orderId;
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getOrder_id() {
        return orderId;
    }

    public void setOrder_id(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemId=" + orderItemId +
                ", item=" + item +
                ", quantity=" + quantity +
                ", price=" + price +
                ", orderId=" + orderId +
                '}';
    }
}
