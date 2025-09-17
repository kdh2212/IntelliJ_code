package com.du.bagunee20250917;

import com.du.bagunee20250917.dao.ProductMapper;
import com.du.bagunee20250917.domain.Product;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

//@SpringBootTest
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// Spring Boot  테스트에서 데이터베이스 설정이 무시되지 않도록 막아주는 설정  ( 위의 코드 설명 )
class Bagunee20250917ApplicationTests {

    @Autowired
    private ProductMapper productMapper;

    @Test
    void contextLoads() {
        List<Product> products = productMapper.findAll();
        for (Product product : products) {
            System.out.println(product);
        }
    }

}
