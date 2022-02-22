package com.asm.market.persistence.crud;

import com.asm.market.persistence.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductCrudRepository extends CrudRepository<Product, Integer> {
}
