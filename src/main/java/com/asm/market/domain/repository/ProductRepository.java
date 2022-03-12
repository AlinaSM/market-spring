package com.asm.market.domain.repository;

import com.asm.market.domain.ProductDomain;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    //En esta interfaz solamente se va a indicar el nombre de los metodos que queremos
    //que cualquier repositorio que vaya a trabajar con productos tenga que implementar

    List<ProductDomain> getAll();
    Optional<List<ProductDomain>> getByCategory(int categoryId);
    Optional<List<ProductDomain>> getScarseProducts(int quantity);
    Optional<ProductDomain> getProduct(int productId);
    ProductDomain save(ProductDomain product);
    void delete(int productId);
}
