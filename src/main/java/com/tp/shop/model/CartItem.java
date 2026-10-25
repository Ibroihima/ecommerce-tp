package com.tp.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;

/** Une ligne du panier : un produit et sa quantité. */
public class CartItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Product product;
    private final int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }

    public int getQuantity() { return quantity; }

    public BigDecimal getSubtotal() {
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
