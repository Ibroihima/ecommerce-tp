package com.tp.shop.model;

import java.math.BigDecimal;

/** Commande validée. */
public class Order {
    private final String reference;
    private final String customerName;
    private final String email;
    private final String address;
    private final BigDecimal total;
    private final int itemCount;

    public Order(String reference, String customerName, String email,
                 String address, BigDecimal total, int itemCount) {
        this.reference = reference;
        this.customerName = customerName;
        this.email = email;
        this.address = address;
        this.total = total;
        this.itemCount = itemCount;
    }

    public String getReference() { return reference; }
    public String getCustomerName() { return customerName; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public BigDecimal getTotal() { return total; }
    public int getItemCount() { return itemCount; }
}
