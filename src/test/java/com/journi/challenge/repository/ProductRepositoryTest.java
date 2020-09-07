package com.journi.challenge.repository;

import com.journi.challenge.models.Product;
import com.journi.challenge.repositories.ProductsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @Mock
    private ProductsRepository productsRepository;

    @BeforeEach
    public void setUp() {
        List<Product> allProducts = new ArrayList<>();
        allProducts.add(Product.builder().id("photobook-square-soft-cover").description("Photobook Square with Soft Cover").price(25.0).currencyCode("EUR").build());
        allProducts.add(Product.builder().id("photobook-square-hard-cover").description("Photobook Square with Hard Cover").price(30.0).currencyCode("USD").build());

        when(productsRepository.findByCurrencyCode("XXX")).thenReturn(allProducts);
    }

    @Test
    public void shouldFindProducts() {
        assertEquals(2, productsRepository.findByCurrencyCode("XXX").size());
        assertTrue(productsRepository.findAll().stream().anyMatch(p -> p.getCurrencyCode().equals("EUR")));
        assertTrue(productsRepository.findAll().stream().anyMatch(p -> p.getCurrencyCode().equals("USD")));
    }
}
