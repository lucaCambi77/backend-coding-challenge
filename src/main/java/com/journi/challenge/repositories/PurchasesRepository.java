package com.journi.challenge.repositories;

import com.journi.challenge.models.Product;
import com.journi.challenge.models.Purchase;
import com.journi.challenge.models.PurchaseStats;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.inject.Named;
import javax.inject.Singleton;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Named
@Singleton
public interface PurchasesRepository extends JpaRepository<Purchase, String> {

}
