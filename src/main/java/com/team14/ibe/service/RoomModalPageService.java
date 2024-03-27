package com.team14.ibe.service;

import com.team14.ibe.dto.response.PromoCodeResponseDTO;
import com.team14.ibe.models.PromoCode;
import com.team14.ibe.repository.PromoCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomModalPageService {

    private final PromoCodeRepository promoCodeRepository;

    @Autowired
    public RoomModalPageService(PromoCodeRepository promoCodeRepository) {
        this.promoCodeRepository = promoCodeRepository;
    }

    public PromoCodeResponseDTO getPromoCodeValidation(String promoCode) {
        PromoCode fetchPromoCode = promoCodeRepository.findByPromoCode(promoCode);
        if(fetchPromoCode != null) {
            PromoCodeResponseDTO promoCodeResponseDTO = new PromoCodeResponseDTO();
            promoCodeResponseDTO.setValid(true);
            promoCodeResponseDTO.setPromocode(fetchPromoCode.getPromoCode());
            promoCodeResponseDTO.setDiscount(fetchPromoCode.getDiscount());
            return promoCodeResponseDTO;
        }
        return new PromoCodeResponseDTO(false, "", 0);
    }
}
