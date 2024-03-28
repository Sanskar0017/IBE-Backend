package com.team14.ibe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PromoCodeDTO {
    private String promoCode;
    private Double discount;
}
