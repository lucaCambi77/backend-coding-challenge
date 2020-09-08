package com.journi.challenge.repositories;

import com.journi.challenge.models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public interface PurchasesRepository extends JpaRepository<Purchase, String> {

}
