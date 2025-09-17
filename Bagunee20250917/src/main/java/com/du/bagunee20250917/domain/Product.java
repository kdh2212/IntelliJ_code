package com.du.bagunee20250917.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Product {
    private Long id;
    private String name;
    private int price;

}
