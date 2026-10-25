package com.tp.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;

/** Un produit du catalogue. */
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final String category;
    private final int stock;
    private final String icon;

    public Product(int id, String name, String description, BigDecimal price,
                   String category, int stock, String icon) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.stock = stock;
        this.icon = icon;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public BigDecimal getPrice() { return price; }
    public String getCategory() { return category; }
    public int getStock() { return stock; }
    public String getIcon() { return icon; }
}
