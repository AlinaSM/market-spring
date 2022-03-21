package com.asm.market.web.controller;

import com.asm.market.domain.ProductDomain;
import com.asm.market.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController //Le indica a Spring que esta clase sera una controller de una API Rest
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    public List<ProductDomain> getAll(){
        return productService.getAll();
    }

    public Optional<ProductDomain> getProduct(int productId){
        return productService.getProduct(productId);
    }

    public Optional<List<ProductDomain>> getByCategory(int categoryId){
        return productService.getByCategory(categoryId);
    }

    public ProductDomain save(ProductDomain productDomain){
        return productService.save(productDomain);
    }

    public boolean delete(int productId){
        return getProduct(productId).map( prod -> {
            productService.delete(productId);
            return true;
        }).orElse( false);
        /*
        if(getProduct(productId).isPresent()){
            productService.delete(productId);
            return true;
        }else{
            return false;
        }
        */
    }

}
