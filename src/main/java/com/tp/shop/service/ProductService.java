package com.tp.shop.service;

import com.tp.shop.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

/** Catalogue de produits en mémoire (pas de base de données pour ce TP). */
public class ProductService {

    private static final ProductService INSTANCE = new ProductService();

    private final List<Product> products = List.of(
        new Product(1, "Laptop Pro 14", "Ordinateur portable 14 pouces, 16 Go RAM, SSD 512 Go",
                new BigDecimal("1299.00"), "Informatique", 10, "💻"),
        new Product(2, "Souris sans fil", "Souris ergonomique silencieuse, 2 piles incluses",
                new BigDecimal("29.90"), "Informatique", 50, "🖱️"),
        new Product(3, "Clavier mécanique", "Clavier AZERTY rétroéclairé, switches rouges",
                new BigDecimal("89.90"), "Informatique", 25, "⌨️"),
        new Product(4, "Smartphone X12", "Écran OLED 6,5 pouces, 128 Go, double SIM",
                new BigDecimal("699.00"), "Téléphonie", 15, "📱"),
        new Product(5, "Écouteurs Bluetooth", "Réduction de bruit, 24 h d'autonomie avec boîtier",
                new BigDecimal("59.90"), "Audio", 40, "🎧"),
        new Product(6, "Casque audio", "Casque fermé haute fidélité, câble détachable",
                new BigDecimal("129.00"), "Audio", 20, "🎵"),
        new Product(7, "Montre connectée", "Suivi sportif, GPS intégré, étanche 50 m",
                new BigDecimal("199.00"), "Accessoires", 12, "⌚"),
        new Product(8, "Sac à dos", "Sac 25 litres avec compartiment pour ordinateur",
                new BigDecimal("49.90"), "Accessoires", 30, "🎒"),
        new Product(9, "Écran 27 pouces 4K", "Dalle IPS, HDR, connectique USB-C",
                new BigDecimal("349.00"), "Informatique", 8, "🖥️"),
        new Product(10, "Enceinte portable", "Bluetooth 5.3, 12 h d'autonomie, résistante à l'eau",
                new BigDecimal("79.90"), "Audio", 18, "🔊")
    );

    public static ProductService getInstance() {
        return INSTANCE;
    }

    public List<Product> findAll() {
        return products;
    }

    public Optional<Product> findById(int id) {
        return products.stream().filter(p -> p.getId() == id).findFirst();
    }

    /** Recherche par mot-clé (nom ou description) et/ou catégorie. Les filtres vides sont ignorés. */
    public List<Product> search(String query, String category) {
        String q = query == null ? "" : query.trim().toLowerCase(Locale.ROOT);
        String cat = category == null ? "" : category.trim();
        return products.stream()
                .filter(p -> cat.isEmpty() || p.getCategory().equals(cat))
                .filter(p -> q.isEmpty()
                        || p.getName().toLowerCase(Locale.ROOT).contains(q)
                        || p.getDescription().toLowerCase(Locale.ROOT).contains(q))
                .collect(Collectors.toList());
    }

    public List<String> getCategories() {
        return products.stream().map(Product::getCategory).distinct().sorted().collect(Collectors.toList());
    }
}
