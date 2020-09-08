package com.journi.challenge.repository;

import com.journi.challenge.configuration.AppConfig;
import com.journi.challenge.repositories.ProductsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {AppConfig.class})
@TestPropertySource(locations = "/test.properties")
public class ProductRepositoryIntegrationTest {

  @Autowired private ProductsRepository productsRepository;

  @Test
  @Sql({"/product1.sql"})
  @Transactional
  public void init() {
    assertEquals(2, productsRepository.findByCurrencyCode("EUR").size());
    assertEquals(1, productsRepository.findByCurrencyCode("USD").size());
  }
}
