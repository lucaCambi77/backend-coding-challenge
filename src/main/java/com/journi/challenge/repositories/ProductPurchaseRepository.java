package com.journi.challenge.repositories;

import com.journi.challenge.models.ProductPurchase;
import com.journi.challenge.models.ProductPurchaseId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPurchaseRepository
    extends JpaRepository<ProductPurchase, ProductPurchaseId> {}
