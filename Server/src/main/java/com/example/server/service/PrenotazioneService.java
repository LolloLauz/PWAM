package com.example.server.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.server.model.Cliente;
import com.example.server.model.Ombrellone;
import com.example.server.model.Prenotazione;
import com.example.server.repository.ClienteRepo;
import com.example.server.repository.OmbrelloneRepo;
import com.example.server.repository.PrenotazioneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepo prenotazioneRepo;

    @Autowired
    private OmbrelloneRepo ombrelloneRepo;

    @Autowired
    private ClienteRepo clienteRepo;

    public List<Prenotazione> getAllPrentoazioni(){
        return prenotazioneRepo.findAll();
    }

    public void addOmbrelloneToPrenotazione(Long idOmbrellone,Long idPrenotazione){
        Ombrellone ombrellone=ombrelloneRepo.getById(idOmbrellone);
        Prenotazione prenotazione=prenotazioneRepo.getById(idPrenotazione);
        prenotazione.addOmbrellone(ombrellone);
        prenotazioneRepo.save(prenotazione);
    }

    public Set<Ombrellone> getOmbrelloniPrenotazione(Long idPrenotazione){
        return prenotazioneRepo.findById(idPrenotazione).orElseThrow().getOmbrelloni();
    }

    public Prenotazione save(Prenotazione prenotazione){
        return prenotazioneRepo.save(prenotazione);
    }

}
