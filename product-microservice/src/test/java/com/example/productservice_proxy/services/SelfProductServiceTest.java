package com.example.productservice_proxy.services;

import com.example.productservice_proxy.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SelfProductServiceTest {

//    @Autowired
    RestTemplateBuilder restTemplateBuilder;
    IProductService selfProductService = new SelfProductService(restTemplateBuilder);

    @Test
    void Test_getAllProducts() {
        List<Product> result= selfProductService.getAllProducts();
        assertNotNull(result);
    }
}