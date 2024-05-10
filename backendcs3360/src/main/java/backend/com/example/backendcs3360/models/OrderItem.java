package backend.com.example.backendcs3360.models;

import java.util.Date;

public class OrderItem {
    private int list_of_items_id;
    private Customer customer;
    private Item item;
    private int quantity;
    private String order_code;
    private Date date_of_purchase;

    public OrderItem(int list_of_items_id, Customer customer, Item item, int quantity, String order_code, Date date_of_purchase) {
        this.list_of_items_id = list_of_items_id;
        this.customer = customer;
        this.item = item;
        this.quantity = quantity;
        this.order_code = order_code;
        this.date_of_purchase = date_of_purchase;
    }

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

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
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
                "order_id=" + list_of_items_id +
                ", item=" + item +
                ", quantity=" + quantity +
                ", order_code='" + order_code + '\'' +
                ", order_date='" + date_of_purchase + '\'' +
                '}';
    }
}