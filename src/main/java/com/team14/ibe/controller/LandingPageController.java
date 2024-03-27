package com.team14.ibe.controller;

import com.team14.ibe.service.LandingPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class LandingPageController {
    @Autowired
    private LandingPageService landingPageService;
    @GetMapping(value="/property-rates", produces= MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Double> getPropertyRates() {
        return landingPageService.getMinimumPricesByDate();
    }

}
