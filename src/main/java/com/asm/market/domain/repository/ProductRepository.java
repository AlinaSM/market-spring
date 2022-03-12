package com.asm.market.domain.repository;

import com.asm.market.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    //En esta interfaz solamente se va a indicar el nombre de los metodos que queremos
    //que cualquier repositorio que vaya a trabajar con productos tenga que implementar

    List<Product> getAll();
    Optional<List<Product>> getByCategory(int categoryId);
    Optional<List<Product>> getScarseProducts(int quantity);
    Optional<Product> getProduct(int productId);
    Product save(Product product);
    void delete(int productId);
}
