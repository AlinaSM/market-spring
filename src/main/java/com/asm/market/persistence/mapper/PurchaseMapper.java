package com.asm.market.persistence.mapper;

import com.asm.market.domain.PurchaseDomain;
import com.asm.market.persistence.entity.Purchase;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = { PurchaseItemMapper.class })
public interface PurchaseMapper {
    @Mappings({
            @Mapping(source = "id", target = "purchaseId" ),
            @Mapping(source = "idClient", target = "clientId" ),
            @Mapping(source = "comment", target = "comment" ),
            @Mapping(source = "status", target = "state" ),
            @Mapping(source = "products", target = "items" ),
    })
    PurchaseDomain toPurchaseDomain(Purchase purchase);
    List<PurchaseDomain> toPurchases(List<Purchase> purchase);


    @InheritInverseConfiguration
    @Mapping(target = "client", ignore = true)
    Purchase toPurchase(PurchaseDomain purchaseDomain);
}
