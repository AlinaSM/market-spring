package com.asm.market.persistence;

import com.asm.market.domain.PurchaseDomain;
import com.asm.market.domain.repository.PurchaseDomainRepository;
import com.asm.market.persistence.crud.PurchaseCrudRepository;
import com.asm.market.persistence.entity.Purchase;
import com.asm.market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PurchaseRepository implements PurchaseDomainRepository {
    @Autowired
    private PurchaseCrudRepository purchaseCrudRepository;

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<PurchaseDomain> getAll() {
      //  System.out.println(purchaseCrudRepository.findAll());
        return mapper.toPurchases( ( List<Purchase> ) purchaseCrudRepository.findAll() );
    }

    @Override
    public Optional<List<PurchaseDomain>> getByClient(String clientId) {
        return purchaseCrudRepository.findByIdClient(clientId)
                .map(purchase -> mapper.toPurchases(purchase));
    }

    @Override
    public PurchaseDomain save(PurchaseDomain purchaseDomain) {
        Purchase purchase  = mapper.toPurchase(purchaseDomain);
        purchase.getProducts().forEach(product -> product.setPurchase(purchase));
        return mapper.toPurchaseDomain(purchaseCrudRepository.save(purchase));
    }
}
