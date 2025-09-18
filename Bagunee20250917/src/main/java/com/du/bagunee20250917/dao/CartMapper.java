package com.du.bagunee20250917.dao;

import com.du.bagunee20250917.domain.CartItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartMapper {
    @Select("SELECT c.*, p.id as product_id, p.name, p.price FROM cart_items c JOIN products p ON c.product_id = p.id")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "productId", column = "product_id"),
            @Result(property = "quantity" ,column = "quantity"),
            @Result(property = "product.id", column = "product_id"),
            @Result(property = "product.name" ,column = "name"),
            @Result(property = "product.price" ,column = "price"),
    })
    List<CartItem> findAll();

    @Insert("INSERT INTO cart_items(product_id, quantity) VALUES(#{productId},#{quantity})")
    void insert(CartItem cartItem);

    @Delete("DELETE FROM cart_items WHERE id= #{id}")
    void delete(Long id);
}
