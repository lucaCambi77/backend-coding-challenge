package com.journi.challenge.models;

import lombok.Builder;
import lombok.Data;

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
    private final String id;

    @Column
    private final String description;

    @Column
    private final Double price;

    @Column
    private final String currencyCode;

}
