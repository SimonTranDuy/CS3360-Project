package backend.com.example.backendcs3360.models;

//@Entity
//@Table(name = "items")
//@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Item {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "itemId")
    private int itemId;
//    @Column(name = "product_name")
    private String productName;
//    @Column(name = "price")
    private Double price;
//    @Column(name = "description")
    private String description;

    public Item() {
    }

    public Item(int itemId, String productName, Double price, String description) {
        this.itemId = itemId;
        this.productName = productName;
        this.price = price;
        this.description = description;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
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

    public void setPrice(Double price) {
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
                "itemId=" + itemId +
                ", product_name='" + productName + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}