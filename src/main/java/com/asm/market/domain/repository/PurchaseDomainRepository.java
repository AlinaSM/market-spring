package com.asm.market.domain.repository;

import com.asm.market.domain.PurchaseDomain;

import java.util.List;
import java.util.Optional;

public interface PurchaseDomainRepository {
    List<PurchaseDomain> getAll();
    Optional<List<PurchaseDomain>> getByClient(String clientId);
    PurchaseDomain save(PurchaseDomain purchaseDomain);
}
