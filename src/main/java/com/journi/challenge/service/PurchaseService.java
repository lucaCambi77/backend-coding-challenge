package com.journi.challenge.service;

import com.journi.challenge.models.Product;
import com.journi.challenge.models.Purchase;
import com.journi.challenge.models.PurchaseRequest;
import com.journi.challenge.models.PurchaseStats;
import com.journi.challenge.repositories.PurchasesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class PurchaseService {

    private final PurchasesRepository purchasesRepository;

    public PurchaseStats getLast30DaysStats() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE.withZone(ZoneId.of("UTC"));

        LocalDateTime start = LocalDate.now().atStartOfDay().minusDays(30);

        List<Purchase> recentPurchases = purchasesRepository.findAll()
                .stream()
                .filter(p -> p.getTimestamp().isAfter(start))
                .sorted(Comparator.comparing(Purchase::getTimestamp))
                .collect(Collectors.toList());

        long countPurchases = recentPurchases.size();
        double totalAmountPurchases = recentPurchases.stream().mapToDouble(Purchase::getTotalValue).sum();
        return new PurchaseStats(
                formatter.format(recentPurchases.get(0).getTimestamp()),
                formatter.format(recentPurchases.get(recentPurchases.size() - 1).getTimestamp()),
                countPurchases,
                totalAmountPurchases,
                totalAmountPurchases / countPurchases,
                recentPurchases.stream().mapToDouble(Purchase::getTotalValue).min().orElse(0.0),
                recentPurchases.stream().mapToDouble(Purchase::getTotalValue).min().orElse(0.0)
        );
    }

    public Purchase save(PurchaseRequest purchaseRequest) {

        Purchase purchase = Purchase.builder()
                .invoiceNumber(purchaseRequest.getInvoiceNumber())
                .timestamp(LocalDateTime.parse(purchaseRequest.getDateTime(), DateTimeFormatter.ISO_DATE_TIME))
                //.productIds(purchaseRequest.getProductIds().stream().map(id -> Product.builder().id(id).build()).collect(Collectors.toSet()))
                .customerName(purchaseRequest.getCustomerName())
                .currencyCode(purchaseRequest.getCurrencyCode())
                .totalValue(purchaseRequest.getAmount()).build();

        return purchasesRepository.save(purchase);
    }

    public List<Purchase> findAll() {
        return purchasesRepository.findAll();
    }
}
