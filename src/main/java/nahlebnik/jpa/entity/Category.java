package nahlebnik.jpa.entity;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @OneToMany(mappedBy = "category")
        private List<Product> products;
        @OneToMany(mappedBy = "category")
        private List<Characteristics> characteristics;
        @Column(name = "category_name")
        private String name;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public List<Product> getProducts() {
                return products;
        }

        public void setProducts(List<Product> products) {
                this.products = products;
        }

        public List<Characteristics> getCharacteristics() {
                return characteristics;
        }

        public void setCharacteristics(List<Characteristics> characteristics) {
                this.characteristics = characteristics;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }
}
