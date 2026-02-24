package com.ebm.apartmentPredictor_Backend.controller;

import com.ebm.apartmentPredictor_Backend.utils.PopulateDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class PopulateDBController {

    @Autowired
    PopulateDB populateDB;

    @PostMapping("/populate")
    public String populateDB (@RequestParam int quantity) {
        populateDB.populateAll(quantity);
        return "DataBase populated with " + quantity + " objects per entity.";
    }
}
