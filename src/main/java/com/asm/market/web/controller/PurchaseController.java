package com.asm.market.web.controller;

import com.asm.market.domain.PurchaseDomain;
import com.asm.market.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping()
    public ResponseEntity<List<PurchaseDomain>> getAll(){
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("client/{idClient}")
    public ResponseEntity<List<PurchaseDomain>> getByClient( @PathVariable("idClient") String clientId){
        return purchaseService.getByClient(clientId)
                .map(purchase ->  new ResponseEntity<>(purchase,HttpStatus.OK ))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public ResponseEntity save(PurchaseDomain purchaseDomain){
        return new ResponseEntity<>(purchaseService.save(purchaseDomain), HttpStatus.CREATED);
    }
}
