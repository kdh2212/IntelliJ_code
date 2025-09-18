package com.du.bagunee20250917.service.impl;

import com.du.bagunee20250917.dao.CartMapper;
import com.du.bagunee20250917.dao.ProductMapper;
import com.du.bagunee20250917.domain.CartItem;
import com.du.bagunee20250917.domain.Product;
import com.du.bagunee20250917.service.ShopService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
    private final ProductMapper productMapper;
    private final CartMapper cartMapper;

    public ShopServiceImpl(ProductMapper productMapper, CartMapper cartMapper) {
        this.productMapper = productMapper;
        this.cartMapper = cartMapper;
    }


    @Override
    public List<Product> getProdcuts() {
        return productMapper.findAll();
    }

    @Override
    public List<CartItem> getCartItems() {
        return cartMapper.findAll();
    }

    @Override
    public void addToCart(Long productId, int quantity) {
        CartItem item = new CartItem();
        item.setProductId(productId);
        item.setQuantity(quantity);
        cartMapper.insert(item);
    }

    @Override
    public void removeFromCart(Long cartItemId) {
        cartMapper.delete(cartItemId);
    }
}
