package nahlebnik.jpa.entity;

import javax.persistence.*;

@Entity
@Table(name = "product_values")
public class CharValues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "values_characteristics")
    private Characteristics characteristics;
    @ManyToOne
    @JoinColumn(name = "values_product")
    private Product product;

    private String values;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Characteristics getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Characteristics characteristics) {
        this.characteristics = characteristics;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }
}
