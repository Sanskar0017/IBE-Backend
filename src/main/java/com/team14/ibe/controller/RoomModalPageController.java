package com.team14.ibe.controller;

import com.azure.core.annotation.Post;
import com.team14.ibe.dto.response.PromoCodeResponseDTO;
import com.team14.ibe.service.RoomModalPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*")
public class RoomModalPageController {

    @Autowired
    private final RoomModalPageService roomModalPageService;

    public RoomModalPageController(RoomModalPageService roomModalPageService) {
        this.roomModalPageService = roomModalPageService;
    }

    @PostMapping("/validatepromocode")
    public ResponseEntity<PromoCodeResponseDTO> validatePromoCode(@RequestBody String promoCode) {
        PromoCodeResponseDTO promoCodeResponseDTO = roomModalPageService.getPromoCodeValidation(promoCode);
        return new ResponseEntity<>(promoCodeResponseDTO, HttpStatus.OK);
    }

}
