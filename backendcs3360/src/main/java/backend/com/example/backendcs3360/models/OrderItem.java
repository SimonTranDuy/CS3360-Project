package backend.com.example.backendcs3360.models;

import jakarta.persistence.*;

import java.util.Date;

//@Entity
//@Table(name = "list_of_items")
public class OrderItem {
    private Item item;
    private int quantity;
    private double total;
    private int order_id;

    public OrderItem(Item item, int quantity, double total, int order_id) {
        this.item = item;
        this.quantity = quantity;
        this.total = total;
        this.order_id = order_id;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "item=" + item +
                ", quantity=" + quantity +
                ", total=" + total +
                ", order_id=" + order_id +
                '}';
    }
}