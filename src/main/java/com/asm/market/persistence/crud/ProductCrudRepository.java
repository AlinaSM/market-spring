package com.asm.market.persistence.crud;

import com.asm.market.persistence.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductCrudRepository extends CrudRepository<Product, Integer> {

    // Query nativo
    // @Query(value = "SELECT * FROM productos WHERE id_categoria = ?", nativeQuery = true )
    // List<Product> getByIdCategory(int idCategory);

    // Query method
    List<Product> findByIdCategoryOrderByNameAsc(int idCategory);

    Optional<List<Product>> findByAmountInStockLessThanAndStatus(int amountInStock, boolean status);



}
