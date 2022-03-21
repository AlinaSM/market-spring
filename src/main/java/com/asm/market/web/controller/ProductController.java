package com.asm.market.web.controller;

import com.asm.market.domain.ProductDomain;
import com.asm.market.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController //Le indica a Spring que esta clase sera una controller de una API Rest
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<List<ProductDomain>> getAll(){
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDomain> getProduct( @PathVariable("id") int productId){
        //El orElse se ejecuta cuando en el map no se ejecuta, y esto cuando ocurre?, justo cuando el optional de getProduct no obtenga resultado
        return productService.getProduct(productId)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping("category/{categoryId}")
    public ResponseEntity<List<ProductDomain>> getByCategory( @PathVariable("categoryId") int categoryId){
        return productService.getByCategory(categoryId)
                .map( products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public ResponseEntity save(@RequestBody ProductDomain productDomain){
        return new ResponseEntity<>(productService.save(productDomain), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete( @PathVariable("id") int productId){
        if(productService.delete(productId)){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        /*return getProduct(productId).map( prod -> {
            productService.delete(productId);
            return true;
        }).orElse( false);

        if(getProduct(productId).isPresent()){
            productService.delete(productId);
            return true;
        }else{
            return false;
        }
        */
    }

}
