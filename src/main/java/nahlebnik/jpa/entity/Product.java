package nahlebnik.jpa.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_name")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_category")
    private Category category;
    @OneToMany(mappedBy = "characteristics")
    private List<CharValues> charValues;

    private String name;

    private Integer price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<CharValues> getCharValues() {
        return charValues;
    }

    public void setCharValues(List<CharValues> charValues) {
        this.charValues = charValues;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
