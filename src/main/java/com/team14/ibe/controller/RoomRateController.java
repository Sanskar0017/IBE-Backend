package com.team14.ibe.controller;

import com.team14.ibe.dto.response.RoomRateResponseDTO;
import com.team14.ibe.service.RoomRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class RoomRateController {
    @Autowired
    private RoomRateService roomRateService;

    @GetMapping("/room-rates")
    public ResponseEntity<String> getRoomRates(
            @RequestParam int roomTypeId,
            @RequestParam String startDate,
            @RequestParam String endDate
    ) {
        try {
            List<RoomRateResponseDTO> roomRates = (List<RoomRateResponseDTO>) roomRateService.getRoomRates(roomTypeId, startDate, endDate);
            return ResponseEntity.ok(roomRates.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
}
