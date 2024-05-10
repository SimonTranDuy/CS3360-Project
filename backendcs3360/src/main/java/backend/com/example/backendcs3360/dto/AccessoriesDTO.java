package backend.com.example.backendcs3360.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "accessories")
public class AccessoriesDTO extends ItemDTO{
    public AccessoriesDTO(int itemId, String productName, double price, String description) {
        super(itemId, productName, price, description);
    }
    @Column(name = "type")
    private String type;

    @Column(name = "material")
    private String material;

    @Column(name = "weight")
    private double weight;

    public AccessoriesDTO() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
