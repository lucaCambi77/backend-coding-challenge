package com.journi.challenge.models;

import lombok.*;

import java.util.List;

/**
 * Request for Purchase
 * amount is the value of the total purchase, in given currencyCode
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRequest {

    private String invoiceNumber;
    private String customerName;
    private String dateTime;
    private List<String> productIds;
    private Double amount;
    private String currencyCode;
}
