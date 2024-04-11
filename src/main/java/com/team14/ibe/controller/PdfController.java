package com.team14.ibe.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class PdfController {

    @PostMapping("/api/send-pdf")
    public String receivePdf(@RequestBody byte[] pdfData) {
        try {
            // Define the file path where the PDF will be saved
            String filePath = "/booking.pdf";

            // Write the PDF data to a file
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(pdfData);
            fos.close();
            System.out.println("mil gaya");
            return "PDF file saved successfully";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error saving PDF file";
        }
    }
}
