package com.example.productservice_proxy.cotrollers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/categories")
public class CategoryController {

    @GetMapping("")
    public String getAllCategories() {
        return "Returning all categories";
    }

    @GetMapping("/{category_id}")
    public String getSingleCategory(@PathVariable("category_id") Long categoryId) {
        return "Getting single category with id: " + categoryId;
    }
}
