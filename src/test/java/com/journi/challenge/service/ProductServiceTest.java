package com.journi.challenge.service;

import com.journi.challenge.models.Product;
import com.journi.challenge.repositories.ProductsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

  @Mock private ProductsRepository productsRepository;

  @Mock private CurrencyConverter currencyConverter;

  @InjectMocks private ProductService productService;

  private static List<Product> allProducts =
      new ArrayList<Product>() {
        {
          add(
              Product.builder()
                  .productId("photobook-square-soft-cover")
                  .description("Photobook Square with Soft Cover")
                  .price(25.0)
                  .currencyCode("EUR")
                  .build());
          add(
              Product.builder()
                  .productId("photobook-square-hard-cover")
                  .description("Photobook Square with Hard Cover")
                  .price(30.0)
                  .currencyCode("USD")
                  .build());
          add(
              Product.builder()
                  .productId("photobook-square-hard-cover")
                  .description("Photobook Square with Hard Cover")
                  .price(30.0)
                  .currencyCode("BRL")
                  .build());
        }
      };

  @BeforeEach
  public void setUp() {

    when(productsRepository.findAll()).thenReturn(allProducts);

    ReflectionTestUtils.setField(
        currencyConverter,
        "supportedCountriesCurrency",
        new HashMap<String, String>() {
          {
            put("BR", "BRL");
          }
        });

    Mockito.lenient()
        .when(currencyConverter.getCurrencyForCountryCode(anyString()))
        .thenCallRealMethod();

    ReflectionTestUtils.setField(
        currencyConverter,
        "currencyEurRate",
        new HashMap<String, Double>() {
          {
            put("EUR", 1.0);
            put("BRL", 5.1480);
          }
        });

    Mockito.lenient()
        .when(currencyConverter.convertEurToCurrency(anyString(), anyDouble()))
        .thenCallRealMethod();
  }

  @Test
  public void shouldConvertPrices() {

    List<Product> products = productService.listProducts("");

    assertEquals(3, products.size());

    assertEquals(
        allProducts.stream()
            .filter(p -> p.getCurrencyCode().equals("EUR"))
            .findFirst()
            .get()
            .getPrice(),
        products.stream()
            .filter(p -> p.getCurrencyCode().equals("EUR"))
            .findFirst()
            .get()
            .getPrice());

    assertEquals(
        allProducts.stream()
            .filter(p -> p.getCurrencyCode().equals("USD"))
            .findFirst()
            .get()
            .getPrice(),
        products.stream()
            .filter(p -> p.getCurrencyCode().equals("USD"))
            .findFirst()
            .get()
            .getPrice());

    assertEquals(
        currencyConverter.convertEurToCurrency(
            "BRL",
            allProducts.stream()
                .filter(p -> p.getCurrencyCode().equals("BRL"))
                .findFirst()
                .get()
                .getPrice()),
        products.stream()
            .filter(p -> p.getCurrencyCode().equals("BRL"))
            .findFirst()
            .get()
            .getPrice());
  }
}
