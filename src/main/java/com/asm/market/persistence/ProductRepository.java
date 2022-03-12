package com.asm.market.persistence;

import com.asm.market.domain.ProductDomain;
import com.asm.market.domain.repository.ProductDomainRepository;
import com.asm.market.persistence.crud.ProductCrudRepository;
import com.asm.market.persistence.entity.Product;
import com.asm.market.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository implements ProductDomainRepository {
    private ProductCrudRepository productCrudRepository;
    private ProductMapper mapper;

    public List<ProductDomain> getAll(){
        List<Product> products = (List<Product>) productCrudRepository.findAll();
        return mapper.toProductsDomain(products);
    }

    @Override
    public Optional<List<ProductDomain>> getByCategory(int categoryId) {
        List<Product> products =  productCrudRepository.findByIdCategoryOrderByNameAsc(categoryId);
        return Optional.of(mapper.toProductsDomain(products));
    }

    @Override
    public Optional<List<ProductDomain>> getScarseProducts(int quantity) {
        Optional<List<Product>> products = productCrudRepository.findByAmountInStockLessThanAndStatus(quantity, true);
        return products.map(prods -> mapper.toProductsDomain(prods));
    }

    @Override
    public Optional<ProductDomain> getProduct(int productId) {
        return productCrudRepository.findById(productId).map( product -> mapper.toProductDomain(product) );
    }

    @Override
    public ProductDomain save(ProductDomain productDomain){
        Product product = mapper.toProduct(productDomain);
        return mapper.toProductDomain(productCrudRepository.save(product));
    }

    @Override
    public void delete(int productId){
        productCrudRepository.deleteById(productId);
    }

}
