package backend.com.example.backendcs3360.dto;

import backend.com.example.backendcs3360.models.Customer;
import backend.com.example.backendcs3360.models.Item;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "list_of_items")
public class ListOfItemsDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "list_of_items_id")
    private int list_of_items_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerDTO customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItemDTO item;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "date_of_purchase")
    private Date date_of_purchase;

    public ListOfItemsDTO(int list_of_items_id, CustomerDTO customer, ItemDTO item, int quantity, Date date_of_purchase) {
        this.list_of_items_id = list_of_items_id;
        this.customer = customer;
        this.item = item;
        this.quantity = quantity;
        this.date_of_purchase = date_of_purchase;
    }

    public int getList_of_items_id() {
        return list_of_items_id;
    }

    public void setList_of_items_id(int list_of_items_id) {
        this.list_of_items_id = list_of_items_id;
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

        public Date getDate_of_purchase() {
        return date_of_purchase;
    }

    public void setDate_of_purchase(Date date_of_purchase) {
        this.date_of_purchase = date_of_purchase;
    }
}
