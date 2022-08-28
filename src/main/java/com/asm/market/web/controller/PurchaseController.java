package com.asm.market.web.controller;

import com.asm.market.domain.PurchaseDomain;
import com.asm.market.domain.service.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Get all the purcheses")
    @ApiResponse( responseCode = "200", description = "OK")
    public ResponseEntity<List<PurchaseDomain>> getAll(){
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("client/{idClient}")
    @Operation(summary = "Get purchuses by a client")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Purchuse not found"),
    })
    public ResponseEntity<List<PurchaseDomain>> getByClient( @Parameter(description = "Client's id", required = true, example = "4546221") @PathVariable("idClient") String clientId){
        return purchaseService.getByClient(clientId)
                .map(purchase ->  new ResponseEntity<>(purchase,HttpStatus.OK ))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    @Operation(summary = "Save purchase")
    @ApiResponse(responseCode = "201", description = "CREATED")
    public ResponseEntity save(PurchaseDomain purchaseDomain){
        return new ResponseEntity<>(purchaseService.save(purchaseDomain), HttpStatus.CREATED);
    }
}
