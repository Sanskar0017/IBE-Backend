package com.team14.ibe.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseResponseDTO {
    private String bookingId;
    private String travelfirstName;
    private String travellastName;
    private String travelphone;
    private String travelemail;
    private String billingfirstName;
    private String billinglastName;
    private String address1;
    private String address2;
    private String billingemail;
    private String billingphone;
    private String cardNumber;
    private String cvvCode;
    private String expMonth;
    private String expYear;
    private String zip;
    private Date startDate;
    private Date endDate;
    private double nightlyRate;
    private double totalAmount;
//    private double promocodeSpecialPrice;
    private double subtotal;
    private double taxAmount;
    private double vatAmount;
    private double totalPrice;
    private String roomname;
    private String country;
    private String state;
    private String city;
    private String room;
    private String promotionTitle;
    private String promotionDescription;
    private double promotionPriceFactor;
    private String promotionPromotionId;
    private int promotionmMinimumDaysOfStay;
    private int adultCount;
    private int childCount;
    private int teenCount;
    private int numberOfRooms;
    private int property;
    private int roomTypeId;
}
