package com.du.bagunee20250917.dao;

import com.du.bagunee20250917.domain.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("SELECT  * FROM products")
    List<Product> findAll();

    @Select("SELECT * FROM  products WHERE id = #{id}")
    Product findById(Long id);
}
