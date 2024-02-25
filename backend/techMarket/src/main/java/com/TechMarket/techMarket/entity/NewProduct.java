package com.TechMarket.techMarket.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class NewProduct {
    private String name;

    private String description;

    private BigDecimal unitPrice;

    private String imageUrl;

    private int unitsInStock;

    private Long category;
}
