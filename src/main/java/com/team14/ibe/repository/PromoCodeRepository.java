package com.team14.ibe.repository;

import com.team14.ibe.models.PromoCode;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Configuration
public interface PromoCodeRepository extends JpaRepository<PromoCode, Integer> {
    PromoCode findByPromoCode(String promoCode);
}
