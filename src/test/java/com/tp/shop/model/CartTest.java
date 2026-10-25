package com.tp.shop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    private Cart cart;
    private Product pen;
    private Product book;

    @BeforeEach
    void setUp() {
        cart = new Cart();
        pen = new Product(1, "Stylo", "Stylo bille", new BigDecimal("10.00"), "Papeterie", 5, "X");
        book = new Product(2, "Cahier", "Cahier A4", new BigDecimal("5.50"), "Papeterie", 20, "X");
    }

    @Test
    void newCartIsEmpty() {
        assertTrue(cart.isEmpty());
        assertEquals(0, cart.getItemCount());
        assertEquals(BigDecimal.ZERO, cart.getTotal());
    }

    @Test
    void addingSameProductMergesQuantities() {
        cart.add(pen, 1);
        cart.add(pen, 2);
        assertEquals(1, cart.getItems().size());
        assertEquals(3, cart.getItemCount());
    }

    @Test
    void quantityIsCappedAtStock() {
        cart.add(pen, 10);
        assertEquals(5, cart.getItemCount());
    }

    @Test
    void addRejectsInvalidQuantity() {
        assertThrows(IllegalArgumentException.class, () -> cart.add(pen, 0));
        assertThrows(IllegalArgumentException.class, () -> cart.add(pen, -3));
    }

    @Test
    void addRejectsNullProduct() {
        assertThrows(IllegalArgumentException.class, () -> cart.add(null, 1));
    }

    @Test
    void totalIsSumOfSubtotals() {
        cart.add(pen, 2);
        cart.add(book, 1);
        assertEquals(new BigDecimal("25.50"), cart.getTotal());
    }

    @Test
    void updateQuantityChangesAndRemovesLine() {
        cart.add(pen, 1);
        cart.updateQuantity(1, 3);
        assertEquals(3, cart.getItemCount());
        cart.updateQuantity(1, 0);
        assertTrue(cart.isEmpty());
    }

    @Test
    void updateUnknownProductDoesNothing() {
        cart.updateQuantity(99, 4);
        assertTrue(cart.isEmpty());
    }

    @Test
    void removeAndClear() {
        cart.add(pen, 1);
        cart.add(book, 1);
        cart.remove(1);
        assertEquals(1, cart.getItems().size());
        cart.clear();
        assertTrue(cart.isEmpty());
    }
}
