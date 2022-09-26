package nahlebnik.jpa.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "characteristics")
public class Characteristics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "characteristics_categories")
    private Category category;
    @OneToMany(mappedBy = "product")
    private List<CharValues> charValues;
    @Column(name = "characteristics_name")
    private String name;


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
}
