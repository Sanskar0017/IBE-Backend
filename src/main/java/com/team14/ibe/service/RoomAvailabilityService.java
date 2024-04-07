////////package com.team14.ibe.service;
////////
////////import com.fasterxml.jackson.databind.JsonNode;
////////import com.fasterxml.jackson.databind.ObjectMapper;
////////import com.team14.ibe.dto.Request.RoomAvailabilityRequestDTO;
////////import com.team14.ibe.dto.response.RoomAvailabilityResponseDTO;
////////import org.springframework.beans.factory.annotation.Value;
////////import org.springframework.http.*;
////////import org.springframework.stereotype.Service;
////////import org.springframework.web.client.RestTemplate;
////////
////////import java.io.IOException;
////////import java.util.*;
////////
////////@Service
////////public class RoomAvailabilityService {
////////    @Value("${graphql.endpoint}")
////////    private String graphqlEndpoint;
////////
////////    @Value("${api.key}")
////////    private String apiKey;
////////
////////    public Map<Long, List<Long>> getRoomAvailabilities(RoomAvailabilityRequestDTO requestDTO) {
////////        RestTemplate restTemplate = new RestTemplate();
////////
////////        HttpHeaders httpHeaders = new HttpHeaders();
////////        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
////////        httpHeaders.set("x-api-key", apiKey);
////////
////////        String graphqlQuery = "{\"query\": \"query MyQuery { listRoomAvailabilities(where: {property_id: {equals: " +
////////                requestDTO.getPropertyId() + "}, room: {room_type_id: {equals: " +
////////                requestDTO.getRoomTypeId() + "}}, date: {gte: \\\"" +
////////                requestDTO.getCheckInDate() + "\\\", lte: \\\"" +
////////                requestDTO.getCheckOutDate() + "\\\"}}) { availability_id booking_id date room_id }}\"}";
////////
////////        HttpEntity<String> entity = new HttpEntity<>(graphqlQuery, httpHeaders);
////////
////////        ResponseEntity<String> responseEntity = restTemplate.exchange(
////////                graphqlEndpoint,
////////                HttpMethod.POST,
////////                entity,
////////                String.class
////////        );
////////
////////        String jsonResponse = responseEntity.getBody();
////////        System.out.println("Response from GraphQL: " + jsonResponse);
////////
////////        Map<Long, List<Long>> roomAvailabilityMap = new HashMap<>();
////////        try {
////////            ObjectMapper objectMapper = new ObjectMapper();
////////            JsonNode root = objectMapper.readTree(jsonResponse);
////////            JsonNode availabilitiesNode = root.path("data").path("listRoomAvailabilities");
////////
////////            for (JsonNode node : availabilitiesNode) {
////////                Long roomId = node.path("room_id").asLong();
////////                Long availabilityId = node.path("availability_id").asLong();
////////
////////                // If room ID is already present in the map, add the availability ID to its list
////////                if (roomAvailabilityMap.containsKey(roomId)) {
////////                    roomAvailabilityMap.get(roomId).add(availabilityId);
////////                } else {
////////                    // If room ID is not present, create a new list and add the availability ID
////////                    List<Long> availabilityIds = new ArrayList<>();
////////                    availabilityIds.add(availabilityId);
////////                    roomAvailabilityMap.put(roomId, availabilityIds);
////////                }
////////            }
////////        } catch (IOException e) {
////////            e.printStackTrace();
////////        }
////////
////////        return roomAvailabilityMap;
////////    }
////////}
//////
//////package com.team14.ibe.service;
//////
//////import com.fasterxml.jackson.databind.JsonNode;
//////import com.fasterxml.jackson.databind.ObjectMapper;
//////import com.team14.ibe.dto.Request.RoomAvailabilityRequestDTO;
//////import org.springframework.beans.factory.annotation.Value;
//////import org.springframework.http.*;
//////import org.springframework.stereotype.Service;
//////import org.springframework.web.client.RestTemplate;
//////
//////import java.io.IOException;
//////import java.util.*;
//////
//////@Service
//////public class RoomAvailabilityService {
//////    @Value("${graphql.endpoint}")
//////    private String graphqlEndpoint;
//////
//////    @Value("${api.key}")
//////    private String apiKey;
//////
//////    public Map<Long, List<Long>> getRoomAvailabilities(RoomAvailabilityRequestDTO requestDTO) {
//////        RestTemplate restTemplate = new RestTemplate();
//////
//////        HttpHeaders httpHeaders = new HttpHeaders();
//////        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//////        httpHeaders.set("x-api-key", apiKey);
//////
//////        String graphqlQuery = "{\"query\": \"query MyQuery { listRoomAvailabilities(where: {property_id: {equals: " +
//////                requestDTO.getPropertyId() + "}, room: {room_type_id: {equals: " +
//////                requestDTO.getRoomTypeId() + "}}, date: {gte: \\\"" +
//////                requestDTO.getCheckInDate() + "\\\", lte: \\\"" +
//////                requestDTO.getCheckOutDate() + "\\\"}}) { availability_id booking_id date room_id }}\"}";
//////
//////        HttpEntity<String> entity = new HttpEntity<>(graphqlQuery, httpHeaders);
//////
//////        ResponseEntity<String> responseEntity = restTemplate.exchange(
//////                graphqlEndpoint,
//////                HttpMethod.POST,
//////                entity,
//////                String.class
//////        );
//////
//////        String jsonResponse = responseEntity.getBody();
//////        System.out.println("Response from GraphQL: " + jsonResponse);
//////
//////        Map<Long, List<Long>> roomAvailabilityMap = new HashMap<>();
//////        try {
//////            ObjectMapper objectMapper = new ObjectMapper();
//////            JsonNode root = objectMapper.readTree(jsonResponse);
//////            JsonNode availabilitiesNode = root.path("data").path("listRoomAvailabilities");
//////
//////            for (JsonNode node : availabilitiesNode) {
//////                Long roomId = node.path("room_id").asLong();
//////                Long availabilityId = node.path("availability_id").asLong();
//////
//////                // If room ID is already present in the map, add the availability ID to its list
//////                if (roomAvailabilityMap.containsKey(roomId)) {
//////                    roomAvailabilityMap.get(roomId).add(availabilityId);
//////                } else {
//////                    // If room ID is not present, create a new list and add the availability ID
//////                    List<Long> availabilityIds = new ArrayList<>();
//////                    availabilityIds.add(availabilityId);
//////                    roomAvailabilityMap.put(roomId, availabilityIds);
//////                }
//////            }
//////        } catch (IOException e) {
//////            e.printStackTrace();
//////        }
//////        System.out.println(roomAvailabilityMap);
//////        return roomAvailabilityMap;
//////    }
//////}
//////
//////
//////// between start and end date, caught room id available along with avail id
//////// discard from database where its overlapping and narrow down the available rooms finally
////
////package com.team14.ibe.service;
////
////import com.fasterxml.jackson.databind.JsonNode;
////import com.fasterxml.jackson.databind.ObjectMapper;
////import com.team14.ibe.dto.Request.RoomAvailabilityRequestDTO;
////import com.team14.ibe.models.BookingConcurrency;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.beans.factory.annotation.Value;
////import org.springframework.http.*;
////import org.springframework.stereotype.Service;
////import org.springframework.web.client.RestTemplate;
////
////import java.io.IOException;
////import java.time.LocalDate;
////import java.time.format.DateTimeFormatter;
////import java.util.*;
////
////@Service
////public class RoomAvailabilityService {
////    @Value("${graphql.endpoint}")
////    private String graphqlEndpoint;
////
////    @Value("${api.key}")
////    private String apiKey;
////
////    @Autowired
////    private BookingConcurrencyService bookingConcurrencyService;
////
////    public void processRoomAvailabilities(RoomAvailabilityRequestDTO requestDTO) {
////        System.out.println("in processRoomAvailabilities");
////        Map<Long, List<Long>> roomAvailabilityMap = getRoomAvailabilities(requestDTO);
////        System.out.println("requestDTO " + requestDTO);
////
////        List<BookingConcurrency> existingBookings = bookingConcurrencyService.getAllBookings();
////        System.out.println("getALlbookings " + existingBookings);
////
////        for (BookingConcurrency booking : existingBookings) {
////            List<Long> availabilityIds = roomAvailabilityMap.get(booking.getRoomId());
////            if (availabilityIds != null) {
////                for (Long availabilityId : availabilityIds) {
////                    LocalDate checkInDate = LocalDate.parse(requestDTO.getCheckInDate(), DateTimeFormatter.ISO_LOCAL_DATE);
////                    LocalDate checkOutDate = LocalDate.parse(requestDTO.getCheckOutDate(), DateTimeFormatter.ISO_LOCAL_DATE);
////
////                    if (booking.isOverlap(checkInDate, checkOutDate)) {
////                        roomAvailabilityMap.get(booking.getRoomId()).remove(availabilityId);
////                    }
////                }
////            }
////        }
////
////        System.out.println("Available Room IDs:");
////        for (Map.Entry<Long, List<Long>> entry : roomAvailabilityMap.entrySet()) {
////            System.out.println("Room ID: " + entry.getKey() + ", Availability IDs: " + entry.getValue());
////        }
////    }
////
////    public Map<Long, List<Long>> getRoomAvailabilities(RoomAvailabilityRequestDTO requestDTO) {
////        RestTemplate restTemplate = new RestTemplate();
////        System.out.println("in getRoomAvailabilities");
////
////        HttpHeaders httpHeaders = new HttpHeaders();
////        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
////        httpHeaders.set("x-api-key", apiKey);
////
////        String graphqlQuery = "{\"query\": \"query MyQuery { listRoomAvailabilities(where: {property_id: {equals: " +
////                requestDTO.getPropertyId() + "}, room: {room_type_id: {equals: " +
////                requestDTO.getRoomTypeId() + "}}, date: {gte: \\\"" +
////                requestDTO.getCheckInDate() + "\\\", lte: \\\"" +
////                requestDTO.getCheckOutDate() + "\\\"}}) { availability_id booking_id date room_id }}\"}";
////
////        HttpEntity<String> entity = new HttpEntity<>(graphqlQuery, httpHeaders);
////        System.out.println("entity " + entity);
////
////
////        ResponseEntity<String> responseEntity = restTemplate.exchange(
////                graphqlEndpoint,
////                HttpMethod.POST,
////                entity,
////                String.class
////        );
////
////        String jsonResponse = responseEntity.getBody();
////        System.out.println("Response from GraphQL: " + jsonResponse);
////
////        Map<Long, List<Long>> roomAvailabilityMap = new HashMap<>();
////        try {
////            ObjectMapper objectMapper = new ObjectMapper();
////            JsonNode root = objectMapper.readTree(jsonResponse);
////            JsonNode availabilitiesNode = root.path("data").path("listRoomAvailabilities");
////
////            for (JsonNode node : availabilitiesNode) {
////                Long roomId = node.path("room_id").asLong();
////                Long availabilityId = node.path("availability_id").asLong();
////
////                // If room ID is already present in the map, add the availability ID to its list
////                if (roomAvailabilityMap.containsKey(roomId)) {
////                    roomAvailabilityMap.get(roomId).add(availabilityId);
////                } else {
////                    // If room ID is not present, create a new list and add the availability ID
////                    List<Long> availabilityIds = new ArrayList<>();
////                    availabilityIds.add(availabilityId);
////                    roomAvailabilityMap.put(roomId, availabilityIds);
////                }
////            }
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////        System.out.println(roomAvailabilityMap);
////        return roomAvailabilityMap;
////    }
////}
//
//
//package com.team14.ibe.service;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.team14.ibe.dto.Request.RoomAvailabilityRequestDTO;
//import com.team14.ibe.models.BookingConcurrency;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.*;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.io.IOException;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//
//@Service
//public class RoomAvailabilityService {
//    @Value("${graphql.endpoint}")
//    private String graphqlEndpoint;
//
//    @Value("${api.key}")
//    private String apiKey;
//
//    @Autowired
//    private BookingConcurrencyService bookingConcurrencyService;
//
//    public void processRoomAvailabilities(RoomAvailabilityRequestDTO requestDTO) {
//        System.out.println("in processRoomAvailabilities");
//        Map<Long, List<Long>> roomAvailabilityMap = getRoomAvailabilities(requestDTO);
//        System.out.println("requestDTO " + requestDTO);
//
//        List<BookingConcurrency> existingBookings = bookingConcurrencyService.getAllBookings();
//        System.out.println("getALlbookings " + existingBookings);
//
//        for (BookingConcurrency booking : existingBookings) {
//            List<Long> availabilityIds = roomAvailabilityMap.get(booking.getRoomIds().get(0)); // Assuming each booking has one room
//            if (availabilityIds != null) {
//                for (Long availabilityId : availabilityIds) {
//                    LocalDate checkInDate = LocalDate.parse(requestDTO.getCheckInDate().substring(0, 10)); // Extract date part without time zone
//                    LocalDate checkOutDate = LocalDate.parse(requestDTO.getCheckOutDate().substring(0, 10)); // Extract date part without time zone
//
//                    if (booking.isOverlap(checkInDate, checkOutDate)) {
//                        roomAvailabilityMap.get(booking.getRoomIds().get(0)).remove(availabilityId); // Assuming each booking has one room
//                    }
//                }
//            }
//        }
//
//        System.out.println("Available Room IDs:");
//        for (Map.Entry<Long, List<Long>> entry : roomAvailabilityMap.entrySet()) {
//            System.out.println("Room ID: " + entry.getKey() + ", Availability IDs: " + entry.getValue());
//        }
//    }
//
//    public Map<Long, List<Long>> getRoomAvailabilities(RoomAvailabilityRequestDTO requestDTO) {
//        RestTemplate restTemplate = new RestTemplate();
//        System.out.println("in getRoomAvailabilities");
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        httpHeaders.set("x-api-key", apiKey);
//
//        String graphqlQuery = "{\"query\": \"query MyQuery { listRoomAvailabilities(where: {property_id: {equals: " +
//                requestDTO.getPropertyId() + "}, room: {room_type_id: {equals: " +
//                requestDTO.getRoomTypeId() + "}}, date: {gte: \\\"" +
//                requestDTO.getCheckInDate() + "\\\", lte: \\\"" +
//                requestDTO.getCheckOutDate() + "\\\"}}) { availability_id booking_id date room_id }}\"}";
//
//        HttpEntity<String> entity = new HttpEntity<>(graphqlQuery, httpHeaders);
//        System.out.println("entity " + entity);
//
//
//        ResponseEntity<String> responseEntity = restTemplate.exchange(
//                graphqlEndpoint,
//                HttpMethod.POST,
//                entity,
//                String.class
//        );
//
//        String jsonResponse = responseEntity.getBody();
//        System.out.println("Response from GraphQL: " + jsonResponse);
//
//        Map<Long, List<Long>> roomAvailabilityMap = new HashMap<>();
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonNode root = objectMapper.readTree(jsonResponse);
//            JsonNode availabilitiesNode = root.path("data").path("listRoomAvailabilities");
//
//            for (JsonNode node : availabilitiesNode) {
//                Long roomId = node.path("room_id").asLong();
//                Long availabilityId = node.path("availability_id").asLong();
//
//                // If room ID is already present in the map, add the availability ID to its list
//                if (roomAvailabilityMap.containsKey(roomId)) {
//                    roomAvailabilityMap.get(roomId).add(availabilityId);
//                } else {
//                    // If room ID is not present, create a new list and add the availability ID
//                    List<Long> availabilityIds = new ArrayList<>();
//                    availabilityIds.add(availabilityId);
//                    roomAvailabilityMap.put(roomId, availabilityIds);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(roomAvailabilityMap);
//        return roomAvailabilityMap;
//    }
//}

package com.team14.ibe.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team14.ibe.dto.Request.CreateBookingRequestDTO;
import com.team14.ibe.dto.Request.RoomAvailabilityRequestDTO;
import com.team14.ibe.dto.response.CreateBookingResponse;
import com.team14.ibe.mapper.CreateBookingResponseDTOMapper;
import com.team14.ibe.models.BookingConcurrency;
import com.team14.ibe.models.PurchaseEntity;
import com.team14.ibe.repository.PurchaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Service
@Slf4j
public class RoomAvailabilityService {
    @Value("${graphql.endpoint}")
    private String graphqlEndpoint;

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    private BookingConcurrencyService bookingConcurrencyService;
    @Autowired
    private BookingMutationService bookingMutationService;
    @Autowired
    private PurchaseRepository purchaseRepository;

//    public void processRoomAvailabilities(RoomAvailabilityRequestDTO requestDTO) {
//        System.out.println("in processRoomAvailabilities");
//        Map<Long, List<Long>> roomAvailabilityMap = getRoomAvailabilities(requestDTO);
//        System.out.println("requestDTO " + requestDTO);
//
//        List<BookingConcurrency> existingBookings = bookingConcurrencyService.getAllBookings();
//        System.out.println("getALlbookings " + existingBookings);
//
//        for (BookingConcurrency booking : existingBookings) {
//            List<Long> availabilityIds = roomAvailabilityMap.get(booking.getRoomTypeId()); // Updated to use getRoomTypeId()
//            if (availabilityIds != null) {
//                for (Long availabilityId : availabilityIds) {
//                    LocalDate checkInDate = LocalDate.parse(requestDTO.getCheckInDate().substring(0, 10)); // Extract date part without time zone
//                    LocalDate checkOutDate = LocalDate.parse(requestDTO.getCheckOutDate().substring(0, 10)); // Extract date part without time zone
//
//                    if (booking.isOverlap(checkInDate, checkOutDate)) {
//                        roomAvailabilityMap.get(booking.getRoomTypeId()).remove(availabilityId); // Updated to use getRoomTypeId()
//                    }
//                }
//            }
//        }
//
//        System.out.println("Available Room IDs:");
//        for (Map.Entry<Long, List<Long>> entry : roomAvailabilityMap.entrySet()) {
//            System.out.println("Room ID: " + entry.getKey() + ", Availability IDs: " + entry.getValue());
//        }
//    }

    public boolean processRoomAvailabilities(RoomAvailabilityRequestDTO requestDTO) {
        System.out.println("step 1");
        Map<Long, List<Long>> roomAvailabilityMap = getRoomAvailabilities(requestDTO);
        // this retrieves the booking from db
        // id checkIndate checkoutdate roomid
        List<BookingConcurrency> existingBookings = bookingConcurrencyService.getAllBookings();


        LocalDate checkInDate = LocalDate.parse(requestDTO.getCheckInDate().substring(0, 10));
        LocalDate checkOutDate = LocalDate.parse(requestDTO.getCheckOutDate().substring(0, 10));


//        for (BookingConcurrency booking : existingBookings) {
//            List<Long> availabilityIds = roomAvailabilityMap.get(booking.getRoomId());
//            if (availabilityIds != null) {
//
//                for (Long availabilityId : availabilityIds) {
//                    // overlap will return the room id and that room id will be removed from map
//
//                    if (booking.isOverlap(checkInDate, checkOutDate)) {
//                        roomAvailabilityMap.get(booking.getRoomId()).remove(availabilityId);
//                    }
//                }
//            }
//        }

        System.out.println("existing booking " + existingBookings);

        for (BookingConcurrency booking : existingBookings) {
            if (booking.isOverlap(checkInDate, checkOutDate)) {
                // If there's an overlap, remove the room from availability map
                roomAvailabilityMap.remove(booking.getRoomId());
                System.out.println("checking merge");
                System.out.println("exising iteration " + booking.toString());
                System.out.println("check on : " + checkInDate + " "  + checkOutDate);

            }
        }

        System.out.println("discarded room id for room type");
        System.out.println("final map is :" + roomAvailabilityMap);

        // at this point allocate the user number of rooms that is required by him a
        // and add the insertion in db

        // Reject or accept the booking request based on the availability status
        int numberOfRooms = 3; // change to get using booking id
        int bookingId = 14; // get the booking id of current booking
        if (roomAvailabilityMap.size() < numberOfRooms) {
            log.info("Not enough room available");
            return false;
        } else {
            System.out.println("Booking request accepted: Available Room IDs:");
            int roomsBooked = 0;

            // call create booking here with all the info using bookingID
            log.info("Creating booking with id : {}" , bookingId);
            PurchaseEntity purchaseEntity = purchaseRepository.getById((long) bookingId);
            CreateBookingRequestDTO createBookingRequestDTO = new CreateBookingResponseDTOMapper().mapToBookingRequestDTO(purchaseEntity);
            CreateBookingResponse createBookingResponse = bookingMutationService.createBooking(createBookingRequestDTO);
            log.info("Booking successfully Created with response : {}", createBookingResponse);
            for (Map.Entry<Long, List<Long>> entry : roomAvailabilityMap.entrySet()) {
                if (roomsBooked >= numberOfRooms) {
                    break; // Stop if the required number of rooms are booked
                }
                System.out.println("Room ID: " + entry.getKey() + ", Availability IDs: " + entry.getValue());


                // add in db
                // call createbooking method to create booking
                // update the avail id to set booking id to 1 to block it


                // Update the BookingConcurrencyService with the new booking details
                bookingConcurrencyService.addBooking(new BookingConcurrency(
                        checkInDate,
                        checkOutDate,
                        entry.getKey()
                ));

                // iterate through the map to get all avail id and set the booking id to the intended booking id of current user
                // using updateRoomAvailability

                for (Long availabilityId : entry.getValue()) {
                    Map<String, Object> updateResult = bookingMutationService.updateRoomAvailability(availabilityId, (long) bookingId);
                    if (updateResult != null) {
                        System.out.println("Room availability updated successfully. Availability ID: " + availabilityId +
                                ", Booking ID: " + bookingId);
                    } else {
                        System.out.println("Failed to update room availability for Availability ID: " + availabilityId);
                        // You may handle error scenarios here
                    }
                }

                roomsBooked++;
            }
            return true;
        }
    }



    public Map<Long, List<Long>> getRoomAvailabilities(RoomAvailabilityRequestDTO requestDTO) {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("in getRoomAvailabilities");
        System.out.println("requestdto to get : " + requestDTO);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("x-api-key", apiKey);

        String graphqlQuery = "{\"query\": \"query MyQuery { listRoomAvailabilities(where: {property_id: {equals: " +
                requestDTO.getPropertyId() + "}, room: {room_type_id: {equals: " +
                requestDTO.getRoomTypeId() + "}}, date: {gte: \\\"" +
                requestDTO.getCheckInDate() + "\\\", lte: \\\"" +
                requestDTO.getCheckOutDate() + "\\\"}}) { availability_id booking_id date room_id }}\"}";

        HttpEntity<String> entity = new HttpEntity<>(graphqlQuery, httpHeaders);
        System.out.println("entity " + entity);


        ResponseEntity<String> responseEntity = restTemplate.exchange(
                graphqlEndpoint,
                HttpMethod.POST,
                entity,
                String.class
        );

        String jsonResponse = responseEntity.getBody();
        System.out.println("Response from GraphQL: " + jsonResponse);

        Map<Long, List<Long>> roomAvailabilityMap = new HashMap<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(jsonResponse);
            JsonNode availabilitiesNode = root.path("data").path("listRoomAvailabilities");

            for (JsonNode node : availabilitiesNode) {
                Long roomId = node.path("room_id").asLong();
                Long availabilityId = node.path("availability_id").asLong();

                // If room ID is already present in the map, add the availability ID to its list
                if (roomAvailabilityMap.containsKey(roomId)) {
                    roomAvailabilityMap.get(roomId).add(availabilityId);
                } else {
                    // If room ID is not present, create a new list and add the availability ID
                    List<Long> availabilityIds = new ArrayList<>();
                    availabilityIds.add(availabilityId);
                    roomAvailabilityMap.put(roomId, availabilityIds);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("room availability: ");
        // use this to check in db  whether or not there is overlap
        // if there is overlap then discard that room id else
        // in the end after iterating all room id tuples
        // left with few room id
        // if number of rooms are enough use that to allocate and add to that db
        // use the create booking method to create the booking with booking id,
        // use the left avail id to mutate the graphql booking id to 1 to block the rooms
        System.out.println(roomAvailabilityMap);
        System.out.println("done");
        return roomAvailabilityMap;
    }
}
