package com.example.productservice_proxy.cotrollers;

import com.example.productservice_proxy.dtos.ProductDto;
import com.example.productservice_proxy.dtos.SearchRequestDto;
import com.example.productservice_proxy.models.Product;
import com.example.productservice_proxy.services.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping("/")
    public Page<ProductDto> searchProduct(@RequestBody SearchRequestDto searchRequestDto){
//        List<Product> result = searchService.searchProduct(searchRequestDto.getQuery(),
//                                                           searchRequestDto.getPageNumber(),
//                                                           searchRequestDto.getSizeOfPage());
//        List<ProductDto> sharebleResult = new LinkedList<>();
//
//        for(Product product: result)
//        {
//            sharebleResult.add(getProductDto(product));
//        }
//        return sharebleResult;
        return searchService.searchProduct(searchRequestDto.getQuery(),
                                           searchRequestDto.getPageNumber(),
                                           searchRequestDto.getSizeOfPage());
    }

    private ProductDto getProductDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());

        return productDto;
    }
}
