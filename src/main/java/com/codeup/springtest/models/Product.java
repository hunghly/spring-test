package com.codeup.springtest.models;

import javax.persistence.*;

@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    String name;

    @Column(columnDefinition = "TEXT")
    String description;

    @Column(columnDefinition = "INT UNSIGNED", name = "price_in_cents")
    int priceInCents;

    @Column(name = "in_stock")
    boolean isInStock;

    public Product() {};

    public Product(long id, String name, String description, int priceInCents, boolean isInStock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.priceInCents = priceInCents;
        this.isInStock = isInStock;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriceInCents() {
        return priceInCents;
    }

    public void setPriceInCents(int priceInCents) {
        this.priceInCents = priceInCents;
    }

    public boolean isInStock() {
        return isInStock;
    }

    public void setInStock(boolean inStock) {
        isInStock = inStock;
    }
}
