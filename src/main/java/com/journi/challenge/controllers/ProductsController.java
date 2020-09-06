package com.journi.challenge.controllers;

import com.journi.challenge.models.Product;
import com.journi.challenge.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductsController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> list(@RequestParam(name = "countryCode", defaultValue = "AT") String countryCode) {
        return productService.listProducts(countryCode);
    }
}
