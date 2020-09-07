package com.journi.challenge.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents a Product the company can sell.
 * Id is of course unique.
 * price is always in Euros.
 */
@Data
@Entity
@Table
@Builder
public class Product {

    @Id
    private String id;

    @Column
    private String description;

    @Column
    private Double price;

    @Column
    private String currencyCode;

}
