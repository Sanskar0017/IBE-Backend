package com.team14.ibe.mapper;//package com.team14.ibe.mapper;
//
//import com.team14.ibe.dto.Request.CreateBookingRequestDTO;
//import com.team14.ibe.models.PurchaseEntity;
//
//import java.time.LocalDate;
//import java.util.Random;
//
//public class CreateBookingResponseDTOMapper {
//
////    public CreateBookingRequestDTO mapToBookingRequestDTO(PurchaseEntity purchase) {
////        CreateBookingRequestDTO bookingRequest = new CreateBookingRequestDTO();
////        bookingRequest.setCheckInDate("");
////        bookingRequest.setCheckOutDate("");
////        bookingRequest.setAdultCount(1);
////        bookingRequest.setChildCount(0);
////        bookingRequest.setTotalCost(0);
////        bookingRequest.setAmountDueAtResort(0);
////        bookingRequest.setGuestName(purchase.getTravelfirstName());
////        bookingRequest.setStatusId(0);
////        bookingRequest.setPropertyId(0);
////        bookingRequest.setPriceFactor(0.0);
////        bookingRequest.setPromotionTitle("");
////        bookingRequest.setPromotionDescription("");
////
////        return bookingRequest;
////    }
//
//    public CreateBookingRequestDTO mapToBookingRequestDTO(PurchaseEntity purchase) {
//        CreateBookingRequestDTO bookingRequest = new CreateBookingRequestDTO();
//        bookingRequest.setCheckInDate(LocalDate.now().toString()); // Set check-in date to today's date
//        bookingRequest.setCheckOutDate(LocalDate.now().plusDays(3).toString()); // Set check-out date to 3 days from today
//        bookingRequest.setAdultCount(1); // Set adult count to 1 for example
//        bookingRequest.setChildCount(0); // Set child count to 0 for example
//        bookingRequest.setTotalCost(generateRandomCost()); // Generate random total cost
//        bookingRequest.setAmountDueAtResort(generateRandomCost()); // Generate random amount due at resort
//        bookingRequest.setGuestName(purchase.getTravelfirstName()); // Set guest name from purchase entity
//        bookingRequest.setStatusId(generateRandomStatusId()); // Generate random status ID
//        bookingRequest.setPropertyId(generateRandomPropertyId()); // Generate random property ID
//        bookingRequest.setPriceFactor(generateRandomPriceFactor()); // Generate random price factor
//        bookingRequest.setPromotionTitle("Random Promotion"); // Set promotion title to example value
//        bookingRequest.setPromotionDescription("Random Promotion Description"); // Set promotion description to example value
//
//        return bookingRequest;
//    }
//
//    private int generateRandomCost() {
//        // Generate random cost between 100 and 1000 for example
//        return new Random().nextInt(901) + 100;
//    }
//
//    private int generateRandomStatusId() {
//        // Generate random status ID between 1 and 5 for example
//        return new Random().nextInt(5) + 1;
//    }
//
//    private int generateRandomPropertyId() {
//        // Generate random property ID between 1000 and 2000 for example
//        return new Random().nextInt(1001) + 1000;
//    }
//
//    private double generateRandomPriceFactor() {
//        // Generate random price factor between 0.5 and 2.0 for example
//        return new Random().nextDouble() * (2.0 - 0.5) + 0.5;
//    }
//
//}


import com.team14.ibe.dto.Request.CreateBookingRequestDTO;
import com.team14.ibe.models.PurchaseEntity;

import java.time.LocalDate;

public class CreateBookingResponseDTOMapper {

    public CreateBookingRequestDTO mapToBookingRequestDTO(PurchaseEntity purchase) {
        CreateBookingRequestDTO bookingRequest = new CreateBookingRequestDTO();
        bookingRequest.setCheckInDate(purchase.getStartDate() != null ? purchase.getStartDate().toString() : LocalDate.now().toString()); // Set check-in date from purchase entity or today's date if null
        bookingRequest.setCheckOutDate(purchase.getEndDate() != null ? purchase.getEndDate().toString() : LocalDate.now().plusDays(3).toString()); // Set check-out date from purchase entity or 3 days from today if null
        bookingRequest.setAdultCount(purchase.getAdultCount());
        bookingRequest.setChildCount(purchase.getChildCount());
        bookingRequest.setTotalCost((int) purchase.getTotalAmount());
        bookingRequest.setAmountDueAtResort((int) purchase.getTotalAmount()); // Assuming amount due at resort is same as total amount
        bookingRequest.setGuestName(purchase.getTravelfirstName());
        bookingRequest.setStatusId(purchase.getStatusId());
        bookingRequest.setPropertyId(purchase.getPropertyId());
        bookingRequest.setPriceFactor(purchase.getPriceFactor());
        bookingRequest.setPromotionTitle(purchase.getPromotionTitle());
        bookingRequest.setPromotionDescription(purchase.getPromotionDescription());

        return bookingRequest;
    }

}
