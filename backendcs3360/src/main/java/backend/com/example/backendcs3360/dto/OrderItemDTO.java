package backend.com.example.backendcs3360.dto;

import backend.com.example.backendcs3360.models.OrderItem;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "list_of_items")
public class OrderItemDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "list_of_items_id")
    private int listOfItemsId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private CustomerDTO customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id")
    private ItemDTO item;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "order_code")
    private String orderCode;

    @Column(name = "date_of_purchase")
    private Date dateOfPurchase;

    public OrderItemDTO() {
    }

    public OrderItemDTO(int listOfItemsId, CustomerDTO customer, ItemDTO item, int quantity, String orderCode,
            Date dateOfPurchase) {
        this.listOfItemsId = listOfItemsId;
        this.customer = customer;
        this.item = item;
        this.quantity = quantity;
        this.orderCode = orderCode;
        this.dateOfPurchase = dateOfPurchase;
    }

    public int getListOfItemsId() {
        return listOfItemsId;
    }

    public void setListOfItemsId(int listOfItemsId) {
        this.listOfItemsId = listOfItemsId;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public ItemDTO getItem() {
        return item;
    }

    public void setItem(ItemDTO item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    @Override
    public String toString() {
        return "OrderItemDTO{" +
                "order_id=" + listOfItemsId +
                ", item=" + item +
                ", quantity=" + quantity +
                ", order_code='" + orderCode + '\'' +
                ", order_date='" + dateOfPurchase + '\'' +
                '}';
    }
    public OrderItem convertToOrderItems(){
        OrderItem newOrderItem = new OrderItem();
        newOrderItem.setCustomer(this.convertToOrderItems().getCustomer());
        newOrderItem.setOrderCode(this.getOrderCode());
        newOrderItem.setListOfItemsId(this.getListOfItemsId());
        newOrderItem.setQuantity(this.getQuantity());
        newOrderItem.setItem(this.convertToOrderItems().getItem());
        newOrderItem.setDateOfPurchase(this.getDateOfPurchase());
        return newOrderItem;
    }
}
