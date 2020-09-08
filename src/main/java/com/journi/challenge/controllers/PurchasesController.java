package com.journi.challenge.controllers;

import com.journi.challenge.models.Purchase;
import com.journi.challenge.models.PurchaseRequest;
import com.journi.challenge.models.PurchaseStats;
import com.journi.challenge.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PurchasesController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/purchases/statistics")
    public PurchaseStats getStats() {
        return purchaseService.getLast30DaysStats();
    }

    @PostMapping("/purchases")
    public Purchase save(@RequestBody PurchaseRequest purchaseRequest) {
        return purchaseService.save(purchaseRequest);
    }

    @GetMapping("/purchases")
    public List<Purchase> list() {
        return purchaseService.findAll();
    }
}
