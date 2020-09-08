package com.journi.challenge.repositories;

import com.journi.challenge.models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.inject.Named;
import javax.inject.Singleton;

@Repository
public interface PurchasesRepository extends JpaRepository<Purchase, String> {}
