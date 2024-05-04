package backend.com.example.backendcs3360.models;

import jakarta.persistence.*;

@Entity
@Table(name = "items")

public abstract class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private int item_id;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "price")
    private double price;
    @Column(name = "description")
    private String description;

    public Items() {
    }

    public Items(int item_id, String productName, double price, String description) {
        this.item_id = item_id;
        this.productName = productName;
        this.price = price;
        this.description = description;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String product_name) {
        this.productName = product_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_name='" + productName + '\'' +
                ", item_id=" + item_id +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}