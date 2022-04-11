package com.example.server.controller;

import com.example.server.model.Ombrellone;
import com.example.server.service.OmbrelloneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/spiaggia/ombrellone")
public class OmbrelloneController {

    @Autowired
    private OmbrelloneService ombrelloneService;


    public List<Ombrellone> getAllOmbrelloni(){
        return ombrelloneService.getAllOmbrelloni();
    }
}
