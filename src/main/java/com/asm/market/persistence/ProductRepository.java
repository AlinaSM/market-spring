package com.asm.market.persistence;

import com.asm.market.persistence.crud.ProductCrudRepository;
import com.asm.market.persistence.entity.Product;

import java.util.List;

public class ProductRepository {
    private ProductCrudRepository productCrudRepository;

    public List<Product> getAll(){
        return (List<Product>) productCrudRepository.findAll();
    }
}
