package com.tp.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/** Panier d'achat stocké en session. La quantité est plafonnée au stock disponible. */
public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Map<Integer, CartItem> items = new LinkedHashMap<>();

    public void add(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Le produit est obligatoire");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("La quantité doit être positive");
        }
        CartItem existing = items.get(product.getId());
        int current = existing == null ? 0 : existing.getQuantity();
        int newQuantity = Math.min(current + quantity, product.getStock());
        if (newQuantity > 0) {
            items.put(product.getId(), new CartItem(product, newQuantity));
        }
    }

    public void updateQuantity(int productId, int quantity) {
        CartItem item = items.get(productId);
        if (item == null) {
            return;
        }
        if (quantity <= 0) {
            items.remove(productId);
            return;
        }
        int capped = Math.min(quantity, item.getProduct().getStock());
        items.put(productId, new CartItem(item.getProduct(), capped));
    }

    public void remove(int productId) {
        items.remove(productId);
    }

    public void clear() {
        items.clear();
    }

    public Collection<CartItem> getItems() {
        return Collections.unmodifiableCollection(items.values());
    }

    public int getItemCount() {
        return items.values().stream().mapToInt(CartItem::getQuantity).sum();
    }

    public BigDecimal getTotal() {
        return items.values().stream()
                .map(CartItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
