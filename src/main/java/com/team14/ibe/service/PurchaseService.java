package com.team14.ibe.service;

import com.team14.ibe.dto.Request.PurchaseDTO;
import com.team14.ibe.dto.response.PurchaseResponseDTO;
import com.team14.ibe.models.PurchaseEntity;
import com.team14.ibe.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//@Service
//public class PurchaseService {
//    private PurchaseRepository purchaseRepository;
//
//    @Autowired
//    public PurchaseService(PurchaseRepository purchaseRepository) {
//        this.purchaseRepository = purchaseRepository;
//    }
//
//    public boolean checkFormData(PurchaseDTO mappedData) {
//        try {
//            PurchaseEntity purchaseEntity = mapDtoToEntity(mappedData);
//
//            purchaseRepository.save(purchaseEntity);
//
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    private PurchaseEntity mapDtoToEntity(PurchaseDTO dto) {
//        PurchaseEntity entity = new PurchaseEntity();
//        entity.setTravelfirstName(dto.getTravelfirstName());
//        entity.setTravellastName(dto.getTravellastName());
//        entity.setTravelphone(dto.getTravelphone());
//        entity.setTravelemail(dto.getTravelemail());
//        entity.setBillingfirstName(dto.getBillingfirstName());
//        entity.setBillinglastName(dto.getBillinglastName());
//        entity.setAddress1(dto.getAddress1());
//        entity.setAddress2(dto.getAddress2());
//        entity.setBillingemail(dto.getBillingemail());
//        entity.setBillingphone(dto.getBillingphone());
//        entity.setCardNumber(dto.getCardNumber());
//        entity.setCvvCode(dto.getCvvCode());
//        entity.setExpMonth(dto.getExpMonth());
//        entity.setExpYear(dto.getExpYear());
//        entity.setZip(dto.getZip());
//        return entity;
//    }
//}
@Service
public class PurchaseService {

    private PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public boolean checkFormData(PurchaseDTO mappedData) {
        try {
            PurchaseEntity purchaseEntity = mapDtoToEntity(mappedData);
            purchaseRepository.save(purchaseEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<PurchaseResponseDTO> getAllPurchases() {
        List<PurchaseEntity> purchases = purchaseRepository.findAll();
        return purchases.stream()
                .map(this::mapEntityToResponseDTO)
                .collect(Collectors.toList());
    }

    private PurchaseEntity mapDtoToEntity(PurchaseDTO dto) {
        PurchaseEntity entity = new PurchaseEntity();
        entity.setTravelfirstName(dto.getTravelfirstName());
        entity.setTravellastName(dto.getTravellastName());
        entity.setTravelphone(dto.getTravelphone());
        entity.setTravelemail(dto.getTravelemail());
        entity.setBillingfirstName(dto.getBillingfirstName());
        entity.setBillinglastName(dto.getBillinglastName());
        entity.setAddress1(dto.getAddress1());
        entity.setAddress2(dto.getAddress2());
        entity.setBillingemail(dto.getBillingemail());
        entity.setBillingphone(dto.getBillingphone());
        entity.setCardNumber(dto.getCardNumber());
        entity.setCvvCode(dto.getCvvCode());
        entity.setExpMonth(dto.getExpMonth());
        entity.setExpYear(dto.getExpYear());
        entity.setZip(dto.getZip());
        return entity;
    }

    private PurchaseResponseDTO mapEntityToResponseDTO(PurchaseEntity entity) {
        PurchaseResponseDTO dto = new PurchaseResponseDTO();
        dto.setId(entity.getId());
        dto.setTravelfirstName(entity.getTravelfirstName());
        dto.setTravellastName(entity.getTravellastName());
        dto.setTravelphone(entity.getTravelphone());
        dto.setTravelemail(entity.getTravelemail());
        dto.setBillingfirstName(entity.getBillingfirstName());
        dto.setBillinglastName(entity.getBillinglastName());
        dto.setAddress1(entity.getAddress1());
        dto.setAddress2(entity.getAddress2());
        dto.setBillingemail(entity.getBillingemail());
        dto.setBillingphone(entity.getBillingphone());
        dto.setCardNumber(entity.getCardNumber());
        dto.setCvvCode(entity.getCvvCode());
        dto.setExpMonth(entity.getExpMonth());
        dto.setExpYear(entity.getExpYear());
        dto.setZip(entity.getZip());
        return dto;
    }
}