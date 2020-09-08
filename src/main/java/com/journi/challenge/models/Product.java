package com.journi.challenge.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/** Represents a Product the company can sell. Id is of course unique. price is always in Euros. */
@Data
@Entity
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product implements java.io.Serializable {

  @Id private String productId;

  @Column private String description;

  @Column private Double price;

  @Column private String currencyCode;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
  private Set<ProductPurchase> productPurchases;
}
