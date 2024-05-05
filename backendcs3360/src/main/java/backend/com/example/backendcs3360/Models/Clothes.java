package backend.com.example.backendcs3360.models;

import jakarta.persistence.*;

@Entity
@Table(name = "clothes")

public class Clothes extends Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand")
    private String brand;
    @Column(name = "size")
    private String size;

    public Clothes(){
        super();
    }

    public Clothes(int item_id, String productName, double price, String description, String brand, String size)
    {
        super(item_id, productName, price, description);
        this.brand = brand;
        this.size = size;
    }

    public String getBrand(){
        return brand;
    }

    public void setBrand(String brand){
        this.brand = brand;
    }

    public String getSize(){
        return size;
    }

    public void setSize(String size){
        this.size = size;
    }

    @Override
    public String toString() {
        return super.toString() + " Clothes {" +
                "brand='" + brand + '\'' +
                ", size='" + size + '\'' +
                '}';
    }    
}