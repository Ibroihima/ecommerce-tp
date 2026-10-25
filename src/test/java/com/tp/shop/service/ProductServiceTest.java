package com.tp.shop.service;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    private final ProductService service = ProductService.getInstance();

    @Test
    void catalogContainsTenProducts() {
        assertEquals(10, service.findAll().size());
    }

    @Test
    void findByIdReturnsProduct() {
        assertTrue(service.findById(1).isPresent());
        assertEquals("Laptop Pro 14", service.findById(1).get().getName());
    }

    @Test
    void findByIdReturnsEmptyWhenUnknown() {
        assertTrue(service.findById(999).isEmpty());
    }

    @Test
    void searchIsCaseInsensitive() {
        assertEquals(1, service.search("LAPTOP", null).size());
    }

    @Test
    void searchByCategory() {
        assertEquals(3, service.search(null, "Audio").size());
    }

    @Test
    void searchByKeywordAndCategory() {
        assertEquals(1, service.search("souris", "Informatique").size());
        assertTrue(service.search("souris", "Audio").isEmpty());
    }

    @Test
    void blankFiltersReturnEverything() {
        assertEquals(10, service.search("   ", "").size());
    }

    @Test
    void categoriesAreDistinctAndSorted() {
        assertEquals(List.of("Accessoires", "Audio", "Informatique", "Téléphonie"), service.getCategories());
    }
}
