package com.example.server.service;

import com.example.server.model.Ombrellone;
import com.example.server.repository.OmbrelloneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OmbrelloneService {

    @Autowired
    OmbrelloneRepo ombrelloneRepo;

    public Ombrellone saveOmbrellone(Ombrellone ombrellone){
        return  ombrelloneRepo.save(ombrellone);
    }

    public List<Ombrellone> getAllOmbrelloni() {
        return ombrelloneRepo.findAll();
    }
}
