package com.team14.ibe.controller;//package com.team14.ibe.controller;
//
//import com.team14.ibe.dto.Request.PurchaseDTO;
//import com.team14.ibe.models.PurchaseEntity;
//import com.team14.ibe.service.PurchaseService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//public class PurchaseController {
//    private PurchaseService purchaseService;
//
//    @Autowired
//    public PurchaseController(PurchaseService purchaseService) {
//        this.purchaseService = purchaseService;
//    }
//
//    @PostMapping("/checkformdata")
//    public ResponseEntity<?> checkFormData(@RequestBody PurchaseDTO mappedData) {
//        System.out.println(mappedData);
//        try {
//            boolean success = purchaseService.checkFormData(mappedData);
//            if (success) {
//                return ResponseEntity.ok("Purchase successful!");
//            } else {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to purchase");
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
//        }
//    }
//}
import com.team14.ibe.dto.Request.PurchaseDTO;
//import com.team14.ibe.dto.Response.PurchaseResponseDTO;
import com.team14.ibe.dto.response.PurchaseResponseDTO;
import com.team14.ibe.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PurchaseController {

    private PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("/checkformdata")
    public ResponseEntity<?> checkFormData(@RequestBody PurchaseDTO mappedData) {
        try {
            boolean success = purchaseService.checkFormData(mappedData);
            if (success) {
                return ResponseEntity.ok("Purchase successful!");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to purchase");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @GetMapping("/purchases")
    public ResponseEntity<List<PurchaseResponseDTO>> getAllPurchases() {
        List<PurchaseResponseDTO> purchases = purchaseService.getAllPurchases();
        return ResponseEntity.ok(purchases);
    }
}