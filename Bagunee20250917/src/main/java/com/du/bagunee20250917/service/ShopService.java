package com.du.bagunee20250917.service;


import com.du.bagunee20250917.domain.CartItem;
import com.du.bagunee20250917.domain.Product;

import java.util.List;

public interface ShopService {
    List<Product> getProdcuts();
    List<CartItem> getCartItems();
    void addToCart(Long productId, int quantity);
    void removeFromCart(Long cartItemId);
}
