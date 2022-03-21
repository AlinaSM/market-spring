package com.asm.market.web.controller;

import com.asm.market.domain.ProductDomain;
import com.asm.market.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController //Le indica a Spring que esta clase sera una controller de una API Rest
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping()
    public List<ProductDomain> getAll(){
        return productService.getAll();
    }

    @GetMapping("{id}")
    public Optional<ProductDomain> getProduct( @PathVariable("id") int productId){
        return productService.getProduct(productId);
    }

    @GetMapping("category/{categoryId}")
    public Optional<List<ProductDomain>> getByCategory( @PathVariable("categoryId") int categoryId){
        return productService.getByCategory(categoryId);
    }

    @PostMapping()
    public ProductDomain save(@RequestBody ProductDomain productDomain){
        return productService.save(productDomain);
    }

    @DeleteMapping("{id}")
    public boolean delete( @PathVariable("id") int productId){
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
