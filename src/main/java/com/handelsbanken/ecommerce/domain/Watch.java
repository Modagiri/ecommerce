package com.handelsbanken.ecommerce.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Watch {
    @Id
    private Long id;
    private Integer unitPrice;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "watch_discount",
            joinColumns =
                    { @JoinColumn(name = "watch_id", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "discount_id", referencedColumnName = "id") })
    private Discount discount;


    public Watch() { }

    public Watch(Long id, String name, Integer unitPrice) {
        this.id = id;
        this.unitPrice = unitPrice;
        this.name = name;
    }

    public Watch(String name, Integer unitPrice, Discount discount) {
        this.unitPrice = unitPrice;
        this.name = name;
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }
    public Discount getDiscount() {
        return discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
