package com.asm.market.web.controller;

import com.asm.market.domain.ProductDomain;
import com.asm.market.domain.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Get all products")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<List<ProductDomain>> getAll(){
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @Operation(summary = "Get products by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
    })
    public ResponseEntity<ProductDomain> getProduct( @Parameter(description = "id of the product", required = true, example = "7")
    @PathVariable("id") int productId){
        //El orElse se ejecuta cuando en el map no se ejecuta, y esto cuando ocurre?, justo cuando el optional de getProduct no obtenga resultado
        return productService.getProduct(productId)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("category/{categoryId}")
    @Operation(summary = "Get products by an id of category")
    @ApiResponses({
            @ApiResponse( responseCode = "200", description = "OK"),
            @ApiResponse( responseCode = "404", description = "Products not found with the id of category given"),
    })
    public ResponseEntity<List<ProductDomain>> getByCategory( @Parameter(description = "Id  of the category", required = true, example = "1") @PathVariable("categoryId") int categoryId){
        return productService.getByCategory(categoryId)
                .map( products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    @Operation(summary = "Save product")
    @ApiResponse(responseCode = "201", description = "Product save")
    public ResponseEntity save(@RequestBody ProductDomain productDomain){
        return new ResponseEntity<>(productService.save(productDomain), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete a product")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
    })
    public ResponseEntity delete( @Parameter(description = "product's id") @PathVariable("id") int productId){
        if(productService.delete(productId)){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
