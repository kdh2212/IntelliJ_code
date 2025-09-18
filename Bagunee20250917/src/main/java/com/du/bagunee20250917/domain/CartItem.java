package com.du.bagunee20250917.domain;

import lombok.Data;
import lombok.Getter;

@Data
public class CartItem {
    private Long id;
    private Long productId;
    private int quantity;
    private Product product;

}
