package model;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    private long id;
    private String name;

    public Product(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_product_id_seq")
    @SequenceGenerator(name = "products_product_id_seq", sequenceName = "products_product_id_seq", allocationSize = 1)
    @Column(name = "product_id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "product_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
