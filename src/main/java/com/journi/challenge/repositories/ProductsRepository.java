package com.journi.challenge.repositories;

import com.journi.challenge.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, String> {

    List<Product> findByCurrencyCode(String currencyCode);
}
