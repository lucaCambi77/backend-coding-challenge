package com.journi.challenge.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * Represents a completed Purchase. invoiceNumber is unique timestamp when the purchase was made.
 * Epoch milliseconds productIds list of product ids included in this purchase customerName name of
 * the customer totalValue total value of this purchase, in EUR
 */
@Data
@Entity
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Purchase implements java.io.Serializable {

  @Id private String invoiceNumber;

  @Column private LocalDateTime timestamp;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "purchase")
  private Set<ProductPurchase> productPurchases;

  @Column private String customerName;

  @Column private Double totalValue;

  @Column private String currencyCode;
}
