package com.team14.ibe.controller;

import com.team14.ibe.dto.response.PromotionResponseDTO;
import com.team14.ibe.dto.response.RoomAvailabilityDTO;
import com.team14.ibe.dto.response.RoomRateDTO;
import com.team14.ibe.dto.response.RoomResponseDTO;
import com.team14.ibe.mapper.RoomDataMapper;
import com.team14.ibe.service.RoomPageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Slf4j
//@CrossOrigin(origins="*")
public class RoomPageController {
    private final RoomPageService roomPageService;

    public RoomPageController(RoomPageService roomPageService) {
        this.roomPageService = roomPageService;
    }

    @GetMapping("/room-types")
    public ResponseEntity<List<RoomResponseDTO>> getAllRoomTypes(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "3") int size, @RequestParam(defaultValue = "false") boolean singleBed, @RequestParam(defaultValue = "false") boolean superDeluxe, @RequestParam(defaultValue = "false") boolean familyDeluxe) {
        List<RoomResponseDTO> roomTypes = roomPageService.getAllRoomTypes(page, size, singleBed, superDeluxe, familyDeluxe);
        return new ResponseEntity<>(roomTypes, HttpStatus.OK);
    }

    @GetMapping("/promotions")
    public ResponseEntity<List<PromotionResponseDTO>> getAllPromotions(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "6") int size, @RequestParam(defaultValue = "false") boolean seniorCitizen, @RequestParam(defaultValue = "false") boolean kduMembership, @RequestParam(defaultValue = "false") boolean longWeekendDiscount, @RequestParam(defaultValue = "false") boolean militaryPersonnelDiscount, @RequestParam(defaultValue = "false") boolean upfrontPaymentDiscount, @RequestParam(defaultValue = "false") boolean weekendDiscount) {
        log.info("promotions");
        List<PromotionResponseDTO> promotions = roomPageService.getAllPromotions(page, size, seniorCitizen, kduMembership, longWeekendDiscount, militaryPersonnelDiscount, upfrontPaymentDiscount, weekendDiscount);
        return new ResponseEntity<>(promotions, HttpStatus.OK);
    }

    @GetMapping("/room-availability")
    public ResponseEntity<List<RoomAvailabilityDTO>> getRoomAvailability() {
        List<RoomAvailabilityDTO> roomAvailability = roomPageService.getRoomAvailability();
        return new ResponseEntity<>(roomAvailability, HttpStatus.OK);
    }

    @GetMapping("/room-rates")
    public ResponseEntity<List<RoomRateDTO>> getRoomRates() {
        List<RoomRateDTO> roomRates = roomPageService.getRoomRate();
        return new ResponseEntity<>(roomRates, HttpStatus.OK);
    }

    @GetMapping("/room-data")
    public ResponseEntity<Map<String, Double>> getRoomData() {
        List<RoomRateDTO> roomRates = roomPageService.getRoomRate();
        List<RoomAvailabilityDTO> roomAvailabilities = roomPageService.getRoomAvailability();
        RoomDataMapper roomDataMapper = new RoomDataMapper();
        Map<String, Double> resultMap = roomDataMapper.mapRoomData(roomRates, roomAvailabilities);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
