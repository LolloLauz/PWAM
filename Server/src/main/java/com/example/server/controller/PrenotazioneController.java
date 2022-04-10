package com.example.server.controller;

import com.example.server.model.Prenotazione;
import com.example.server.service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController()
@RequestMapping("/cliente/prenotazione")
public class PrenotazioneController {


    @Autowired
    private PrenotazioneService prenotazioneService;


    @GetMapping("/getAll")
    public List<Prenotazione> getAllPrenotazioni(){
        return prenotazioneService.getAllPrentoazioni();
    }

    @PostMapping("/addOmbrellone")
    public void addOmbrelloneToPrenotazione(@RequestBody AddOmbrelloneRequest ombrelloneRequest){
        prenotazioneService.addOmbrelloneToPrenotazione(ombrelloneRequest.getIdOmbrellone(),ombrelloneRequest.getIdPrenotazione());
    }
}
