package com.team14.ibe.controller;

import com.azure.core.annotation.Post;
import com.team14.ibe.dto.PromoCodeDTO;
import com.team14.ibe.dto.response.PromoCodeResponseDTO;
import com.team14.ibe.service.RoomModalPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins="*")
public class RoomModalPageController {

    @Autowired
    private final RoomModalPageService roomModalPageService;

    public RoomModalPageController(RoomModalPageService roomModalPageService) {
        this.roomModalPageService = roomModalPageService;
    }

    @PostMapping("/validatepromocode")
    public ResponseEntity<PromoCodeResponseDTO> validatePromoCode(@RequestParam String promoCode) {
        PromoCodeResponseDTO promoCodeResponseDTO = roomModalPageService.getPromoCodeValidation(promoCode);
        return new ResponseEntity<>(promoCodeResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/promo-code-insertion")
    public ResponseEntity<Void> addPromoCodes(@RequestBody List<PromoCodeDTO> promoCodeDTOs) {
        for (PromoCodeDTO promoCodeDTO : promoCodeDTOs) {
            roomModalPageService.insertPromoCode(promoCodeDTO);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
