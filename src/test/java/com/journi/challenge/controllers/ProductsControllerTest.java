package com.journi.challenge.controllers;

import com.journi.challenge.models.Product;
import com.journi.challenge.service.ProductService;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductsController.class)
@TestPropertySource(locations = "/test.properties")
@ExtendWith(SpringExtension.class)
class ProductsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void shouldListProductsWithCurrencyCodeAndConvertedPriceDefault() throws Exception {
        List<Product> allProducts = new ArrayList<>();
        allProducts.add(Product.builder().id("photobook-square-soft-cover").description("Photobook Square with Soft Cover").price(25.0).currencyCode("EUR").build());
        allProducts.add(Product.builder().id("photobook-square-hard-cover").description("Photobook Square with Hard Cover").price(30.0).currencyCode("USD").build());

        Mockito.lenient().when(productService.listProducts(anyString())).thenReturn(allProducts);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", IsEqual.equalTo(2)));
    }

    @Test
    public void shouldListProductsWithCurrencyCodeAndConvertedPriceBR() throws Exception {
        mockMvc.perform(get("/products?countryCode=BR"))
                .andExpect(status().isOk());

        verify(productService).listProducts("BR");
    }
}
