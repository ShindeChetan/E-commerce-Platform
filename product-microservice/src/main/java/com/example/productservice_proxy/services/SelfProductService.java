package com.example.productservice_proxy.services;

import com.example.productservice_proxy.dtos.ProductDto;
import com.example.productservice_proxy.models.Categories;
import com.example.productservice_proxy.models.Product;
import com.example.productservice_proxy.repostries.ProductRepo;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class SelfProductService implements IProductService{

        RestTemplateBuilder restTemplateBuilder;
//        ProductDto productRepo;
    ProductRepo productRepo;

    public SelfProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }
    public SelfProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public SelfProductService() {
    }

    @Override
    public List<Product> getAllProducts() {
            RestTemplate restTemplate = restTemplateBuilder.build();
            ResponseEntity<ProductDto[]> productDtos =
                    restTemplate
                            .getForEntity("https://fakestoreapi.com/products", ProductDto[].class);


            List<Product> answer = new ArrayList<>();

//            for (ProductDto productDto: productDtos.getBody()) {
//                Product product = new Product();
//                product.setId(productDto.getId());
//                product.setTitle(productDto.getTitle());
//                product.setPrice(productDto.getPrice());
//                Categories category = new Categories();
//                category.setName(productDto.getCategory());
//                product.setCategory(category);
//                product.setImageUrl(productDto.getImageUrl());
//                answer.add(product);
////            }
            return answer;
        }


        @Override
        public Product getSingleProduct(Long productId) {
//            RestTemplate restTemplate = restTemplateBuilder.build();
//            ProductDto productDto = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",
//                                                            ProductDto.class, productId).getBody();
//            Product product =  getProduct(productDto);
//            return product;
            return null;
        }

//        @Override
//        public Product addNewProduct(Product product) {
//            RestTemplate restTemplate = restTemplateBuilder.build();
//            restTemplate.postForEntity("https://fakestoreapi.com/products", productDto, ProductDto.class);
//            Product product = getProduct(productDto);
//            return product;
//        }

        @Override
        public Product addNewProduct(Product product) {
            this.productRepo.save(product);
            return product;
        }

        @Override
        public Product updateProduct(Long productId, Product product) {
            return null;
        }

        @Override
        public String deleteProduct(Long productId) {
            return null;
        }

    private Product getProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Categories category = new Categories();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(productDto.getImageUrl());
        product.setDescription(productDto.getDescription());
        return product;
    }
}
