package com.asm.market.domain.service;

import com.asm.market.domain.ProductDomain;
import com.asm.market.domain.repository.ProductDomainRepository;
import com.asm.market.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductDomainRepository productDomainRepository;

    public List<ProductDomain> getAll(){
        return productDomainRepository.getAll();
    }

    public Optional<ProductDomain> getProduct(int productId) {
        return productDomainRepository.getProduct(productId);
    }

    public Optional<List<ProductDomain>> getByCategory(int categoryId){
        return productDomainRepository.getByCategory(categoryId);
    }

    public ProductDomain save(ProductDomain productDomain){
        return productDomainRepository.save(productDomain);
    }

    public boolean delete(int productId){
        return getProduct(productId).map( prod -> {
            productDomainRepository.delete(productId);
            return true;
        }).orElse( false);
        /*
        if(getProduct(productId).isPresent()){
            productDomainRepository.delete(productId);
            return true;
        }else{
            return false;
        }
        */
     }

}
