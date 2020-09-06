package com.journi.challenge.service;

import com.journi.challenge.models.Product;
import com.journi.challenge.repositories.ProductsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProductService {

    private ProductsRepository productsRepository;
    private CurrencyConverter currencyConverter;

    public List<Product> listProducts(String countryCode) {

        List<Product> list = productsRepository.findAll();

        return list.stream().map(p -> Product.builder().id(p.getId())
                .description(p.getDescription())
                .price(currencyConverter.convertEurToCurrency(p.getCurrencyCode(), p.getPrice()))
                .currencyCode(p.getCurrencyCode())
                .build()).collect(Collectors.toList());
    }

}
