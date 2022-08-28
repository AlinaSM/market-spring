package com.asm.market.domain.service;

import com.asm.market.domain.PurchaseDomain;
import com.asm.market.domain.repository.PurchaseDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseDomainRepository purchaseDomainRepository;

    public List<PurchaseDomain> getAll(){
          return purchaseDomainRepository.getAll();
    }

    public Optional<List<PurchaseDomain>> getByClient(String clientId){
        return purchaseDomainRepository.getByClient(clientId);
    }

    public PurchaseDomain save(PurchaseDomain purchaseDomain){
        return purchaseDomainRepository.save(purchaseDomain);
    }

}
