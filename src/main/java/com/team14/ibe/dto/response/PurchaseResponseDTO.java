//package com.team14.ibe.dto.response;
//
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class PurchaseResponseDTO {
//    private Long id;
//    private String travelfirstName;
//    private String travellastName;
//    private String travelphone;
//    private String travelemail;
//    private String billingfirstName;
//    private String billinglastName;
//    private String address1;
//    private String address2;
//    private String billingemail;
//    private String billingphone;
//    private String cardNumber;
//    private String cvvCode;
//    private String expMonth;
//    private String expYear;
//    private String zip;
//}


package com.team14.ibe.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseResponseDTO {
    private Long id;
    private String bookingId;
    private String travelfirstName;
    private String travellastName;
    private String travelphone;
    private String travelemail;
    private String billingfirstName;
    private String billinglastName;
    private String address1;
    private String address2;
    private String country;
    private String state;
    private String city;
    private String billingemail;
    private String billingphone;
    private String cardNumber;
    private String expMonth;
    private String expYear;
    private String zip;
    private Date startDate;
    private Date endDate;
    private double nightlyRate;
    private double totalAmount;
    private double promocodeSpecialPrice;
    private double subtotal;
    private double taxAmount;
    private double vatAmount;
    private double totalPrice;
}
