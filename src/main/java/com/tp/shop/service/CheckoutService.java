package com.tp.shop.service;

import com.tp.shop.model.Cart;
import com.tp.shop.model.Order;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

/** Validation du formulaire de commande et création de la commande. */
public class CheckoutService {

    private static final CheckoutService INSTANCE = new CheckoutService();
    private static final Pattern EMAIL = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    private static final int MIN_ADDRESS_LENGTH = 10;

    private final AtomicLong sequence = new AtomicLong(1000);

    public static CheckoutService getInstance() {
        return INSTANCE;
    }

    /** Retourne une map champ -> message d'erreur (vide si tout est valide). */
    public Map<String, String> validate(String name, String email, String address) {
        Map<String, String> errors = new LinkedHashMap<>();
        if (name == null || name.isBlank()) {
            errors.put("name", "Le nom est obligatoire");
        }
        if (email == null || !EMAIL.matcher(email.trim()).matches()) {
            errors.put("email", "Adresse e-mail invalide");
        }
        if (address == null || address.trim().length() < MIN_ADDRESS_LENGTH) {
            errors.put("address", "Adresse trop courte (" + MIN_ADDRESS_LENGTH + " caractères minimum)");
        }
        return errors;
    }

    /** Crée la commande à partir du panier, puis vide le panier. */
    public Order placeOrder(Cart cart, String name, String email, String address) {
        if (cart == null || cart.isEmpty()) {
            throw new IllegalStateException("Le panier est vide");
        }
        Order order = new Order("CMD-" + sequence.incrementAndGet(), name.trim(), email.trim(),
                address.trim(), cart.getTotal(), cart.getItemCount());
        cart.clear();
        return order;
    }
}
