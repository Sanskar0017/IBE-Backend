//package com.team14.ibe.controller;
//
//import com.team14.ibe.dto.Request.RoomAvailabilityRequestDTO;
//import com.team14.ibe.dto.response.RoomAvailabilityResponseDTO;
//import com.team14.ibe.service.RoomAvailabilityService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//public class UserBooking {
//
//    @Autowired
//    private RoomAvailabilityService roomAvailabilityService;
//
//    @GetMapping("/roomAvailabilities")
//    public void getRoomAvailabilities(
//            @RequestBody RoomAvailabilityRequestDTO roomAvailabilityRequestDTO
//    ) {
//        RoomAvailabilityRequestDTO requestDTO = new RoomAvailabilityRequestDTO();
//        requestDTO.setPropertyId(roomAvailabilityRequestDTO.getPropertyId());
//        requestDTO.setRoomTypeId(roomAvailabilityRequestDTO.getRoomTypeId());
//        requestDTO.setCheckInDate(roomAvailabilityRequestDTO.getCheckInDate());
//        requestDTO.setCheckOutDate(roomAvailabilityRequestDTO.getCheckOutDate());
//        System.out.println(roomAvailabilityRequestDTO);
////        roomAvailabilityService.getRoomAvailabilities(requestDTO);
//        roomAvailabilityService.processRoomAvailabilities(requestDTO);
//    }
//}


package com.team14.ibe.controller;

import com.team14.ibe.dto.Request.RoomAvailabilityRequestDTO;
import com.team14.ibe.service.RoomAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserBooking {

    @Autowired
    private RoomAvailabilityService roomAvailabilityService;

//    @PostMapping("/roomAvailabilities")
//    public void getRoomAvailabilities(@RequestBody RoomAvailabilityRequestDTO roomAvailabilityRequestDTO) {
//        roomAvailabilityService.processRoomAvailabilities(roomAvailabilityRequestDTO);
//    }
}
