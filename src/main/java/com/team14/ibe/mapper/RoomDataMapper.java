package com.team14.ibe.mapper;

import com.team14.ibe.dto.response.RoomAvailabilityDTO;
import com.team14.ibe.dto.response.RoomRateDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomDataMapper {

    public Map<String, Double> mapRoomData(List<RoomRateDTO> roomRates, List<RoomAvailabilityDTO> roomAvailabilities) {
        Map<String, Double> resultMap = new HashMap<>();

        // Step 1: Combine the data based on room type and date
        Map<String, Map<String, Double>> combinedData = combineData(roomRates, roomAvailabilities);

        // Step 2: Calculate the minimum rate for each room type and take average for available days
        for (Map.Entry<String, Map<String, Double>> entry : combinedData.entrySet()) {
            String roomType = entry.getKey();
            Map<String, Double> ratesByDate = entry.getValue();

            double totalRate = 0.0;
            int availableDays = 0;

            // Calculate total rate and count available days
            for (Map.Entry<String, Double> rateEntry : ratesByDate.entrySet()) {
                Double rate = rateEntry.getValue();
                if (rate != null) {
                    totalRate += rate;
                    availableDays++;
                }
            }

            // Calculate average rate for available days
            double averageRate = availableDays > 0 ? totalRate / availableDays : 0.0;
            resultMap.put(roomType, averageRate);
        }

        return resultMap;
    }

//    private Map<String, Map<String, Double>> combineData(List<RoomRateDTO> roomRates, List<RoomAvailabilityDTO> roomAvailabilities) {
//        Map<String, Map<String, Double>> combinedData = new HashMap<>();
//
//        // Combine room rates by room type and date
//        for (RoomRateDTO roomRate : roomRates) {
//            String roomType = roomRate.getRoomTypeName();
//            String date = roomRate.getDate();
//            double rate = roomRate.getBasicNightlyRate();
//
//            if (!combinedData.containsKey(roomType)) {
//                combinedData.put(roomType, new HashMap<>());
//            }
//            combinedData.get(roomType).put(date, rate);
//        }
//
//        // Combine room availabilities by room type and date
//        for (RoomAvailabilityDTO roomAvailability : roomAvailabilities) {
//            String roomType = roomAvailability.getRoomTypeName();
//            String date = roomAvailability.getDate();
//
//            if (!combinedData.containsKey(roomType)) {
//                combinedData.put(roomType, new HashMap<>());
//            }
//            // If the date already exists for this room type, ignore it because we only need rates for available dates
//            if (!combinedData.get(roomType).containsKey(date)) {
//                combinedData.get(roomType).put(date, null);
//            }
//        }
//
//        return combinedData;
//    }
private Map<String, Map<String, Double>> combineData(List<RoomRateDTO> roomRates, List<RoomAvailabilityDTO> roomAvailabilities) {
    Map<String, Map<String, Double>> combinedData = new HashMap<>();

    // Combine room rates by room type and date
    for (RoomRateDTO roomRate : roomRates) {
        String roomType = roomRate.getRoomTypeName();
        String date = roomRate.getDate();
        double rate = roomRate.getBasicNightlyRate();

        if (!combinedData.containsKey(roomType)) {
            combinedData.put(roomType, new HashMap<>());
        }
        combinedData.get(roomType).put(date, rate);
    }

    // Combine room availabilities by room type and date
    for (RoomAvailabilityDTO roomAvailability : roomAvailabilities) {
        int roomId = roomAvailability.getRoomId();
        String date = roomAvailability.getDate();

        // Check if the room ID is available for the given date
        for (Map.Entry<String, Map<String, Double>> entry : combinedData.entrySet()) {
            String roomType = entry.getKey();
            Map<String, Double> ratesByDate = entry.getValue();

            if (ratesByDate.containsKey(date)) {
                if (!combinedData.containsKey(roomType)) {
                    combinedData.put(roomType, new HashMap<>());
                }
                combinedData.get(roomType).put(date, null);
            }
        }
    }

    return combinedData;
}

}

