package com.journi.challenge.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.journi.challenge.models.Product;
import com.journi.challenge.models.Purchase;
import com.journi.challenge.models.PurchaseRequest;
import com.journi.challenge.models.PurchaseStats;
import com.journi.challenge.service.PurchaseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PurchasesController.class)
@TestPropertySource(locations = "/test.properties")
@ExtendWith(SpringExtension.class)
class PurchasesControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private PurchaseService purchaseService;

  @Test
  public void testPurchaseCurrencyCodeEUR() throws Exception {
    PurchaseRequest purchase =
        PurchaseRequest.builder()
            .currencyCode("EUR")
            .invoiceNumber("1")
            .dateTime("2020-01-01T10:00:00+01:00")
            .productIds(Arrays.asList("product1", "product2"))
            .amount(25.34)
            .currencyCode("EUR")
            .customerName("customer")
            .build();

    String purchaseJson = new ObjectMapper().writeValueAsString(purchase);

    mockMvc
        .perform(post("/purchases").contentType(MediaType.APPLICATION_JSON).content(purchaseJson))
        .andExpect(status().isOk());

    verify(purchaseService, times(1)).save(purchase);
  }

  @Test
  public void testPurchaseStatistics() {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime firstDate = now.minusDays(20);
    DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE.withZone(ZoneId.of("UTC"));
    /*
           // Inside window purchases
           purchaseService.save(new Purchase("1", firstDate, Collections.emptyList(), "", 10.0));
           purchaseService.save(new Purchase("1", firstDate.plusDays(1), Collections.emptyList(), "", 10.0));
           purchaseService.save(new Purchase("1", firstDate.plusDays(2), Collections.emptyList(), "", 10.0));
           purchaseService.save(new Purchase("1", firstDate.plusDays(3), Collections.emptyList(), "", 10.0));
           purchaseService.save(new Purchase("1", firstDate.plusDays(4), Collections.emptyList(), "", 10.0));
           purchaseService.save(new Purchase("1", firstDate.plusDays(5), Collections.emptyList(), "", 10.0));
           purchaseService.save(new Purchase("1", firstDate.plusDays(6), Collections.emptyList(), "", 10.0));
           purchaseService.save(new Purchase("1", firstDate.plusDays(7), Collections.emptyList(), "", 10.0));
           purchaseService.save(new Purchase("1", firstDate.plusDays(8), Collections.emptyList(), "", 10.0));
           purchaseService.save(new Purchase("1", firstDate.plusDays(9), Collections.emptyList(), "", 10.0));

           // Outside window purchases
           purchaseService.save(new Purchase("1", now.minusDays(31), Collections.emptyList(), "", 10.0));
           purchaseService.save(new Purchase("1", now.minusDays(31), Collections.emptyList(), "", 10.0));
           purchaseService.save(new Purchase("1", now.minusDays(32), Collections.emptyList(), "", 10.0));
           purchaseService.save(new Purchase("1", now.minusDays(33), Collections.emptyList(), "", 10.0));
           purchaseService.save(new Purchase("1", now.minusDays(34), Collections.emptyList(), "", 10.0));
           purchaseService.save(new Purchase("1", now.minusDays(35), Collections.emptyList(), "", 10.0));


           PurchaseStats purchaseStats = purchasesController.getStats();
           assertEquals(formatter.format(firstDate), purchaseStats.getFrom());
           assertEquals(formatter.format(firstDate.plusDays(9)), purchaseStats.getTo());
           assertEquals(10, purchaseStats.getCountPurchases());
           assertEquals(100.0, purchaseStats.getTotalAmount());
           assertEquals(10.0, purchaseStats.getAvgAmount());
           assertEquals(10.0, purchaseStats.getMinAmount());
           assertEquals(10.0, purchaseStats.getMaxAmount());

    */
  }
}
