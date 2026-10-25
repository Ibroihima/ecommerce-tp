package com.tp.shop.service;

import com.tp.shop.model.Cart;
import com.tp.shop.model.Order;
import com.tp.shop.model.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutServiceTest {

    private final CheckoutService service = CheckoutService.getInstance();

    @Test
    void validDataProducesNoError() {
        Map<String, String> errors = service.validate("Awa Diop", "awa@example.com", "12 rue des Lilas, Lille");
        assertTrue(errors.isEmpty());
    }

    @Test
    void invalidDataProducesErrorsForEachField() {
        Map<String, String> errors = service.validate("  ", "pas-un-email", "court");
        assertEquals(3, errors.size());
        assertTrue(errors.containsKey("name"));
        assertTrue(errors.containsKey("email"));
        assertTrue(errors.containsKey("address"));
    }

    @Test
    void nullDataIsRejected() {
        assertEquals(3, service.validate(null, null, null).size());
    }

    @Test
    void placeOrderBuildsOrderAndClearsCart() {
        Cart cart = new Cart();
        Product p = new Product(1, "Stylo", "Stylo bille", new BigDecimal("10.00"), "Papeterie", 5, "X");
        cart.add(p, 2);

        Order order = service.placeOrder(cart, " Awa ", "awa@example.com", "12 rue des Lilas, Lille");

        assertTrue(order.getReference().startsWith("CMD-"));
        assertEquals("Awa", order.getCustomerName());
        assertEquals(new BigDecimal("20.00"), order.getTotal());
        assertEquals(2, order.getItemCount());
        assertTrue(cart.isEmpty());
    }

    @Test
    void placeOrderFailsWithEmptyCart() {
        Cart cart = new Cart();
        assertThrows(IllegalStateException.class,
                () -> service.placeOrder(cart, "Awa", "awa@example.com", "12 rue des Lilas"));
        assertThrows(IllegalStateException.class,
                () -> service.placeOrder(null, "Awa", "awa@example.com", "12 rue des Lilas"));
    }
}
