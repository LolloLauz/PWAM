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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.ArrayList;
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

    public Set<Ombrellone> getOmbrelloniPrenotazione(Long idPrenotazione,String email){
        if(clienteRepo.findClienteByEmail(email).getPrenotazioni().contains(prenotazioneRepo.getById(idPrenotazione))){
            return prenotazioneRepo.findById(idPrenotazione).orElseThrow().getOmbrelloni();
        }else {
            return null;
        }
    }

    public Prenotazione save(Prenotazione prenotazione){
        return prenotazioneRepo.save(prenotazione);
    }

    public Prenotazione addNewPrenotazione(Prenotazione prenotazione, String email) {
        Cliente cliente = clienteRepo.findClienteByEmail(email);
        cliente.addPrenotazione(prenotazione);
        return prenotazioneRepo.save(prenotazione);
    }

    public List<Ombrellone> getOmbrelloniLiberiInUnPeriodo(LocalDate dataInizio, LocalDate dataFine) {
        List<Ombrellone> ombrelloni=new ArrayList<>();
        ombrelloni.addAll(ombrelloneRepo.findAll());
        List<Prenotazione> prenotazioni=new ArrayList<>();
        prenotazioni.addAll(prenotazioneRepo.findAll());
        for(Prenotazione prenotazione:prenotazioni){
            if(checkDate(prenotazione,dataInizio,dataFine)){
                ombrelloni.removeAll(prenotazione.getOmbrelloni());
            }
        }

        return ombrelloni;

    }



    private boolean checkDate(Prenotazione prenotazione, LocalDate dataInizio, LocalDate dataFine) {
        return (prenotazione.getDataInizio().isBefore(dataInizio) && prenotazione.getDataFine().isAfter(dataFine)) ||
                (prenotazione.getDataInizio().isAfter(dataInizio) && prenotazione.getDataFine().isBefore(dataFine)) ||
                (prenotazione.getDataInizio().isBefore(dataInizio) && prenotazione.getDataFine().isAfter(dataInizio)) ||
                (prenotazione.getDataInizio().isBefore(dataFine) && prenotazione.getDataFine().isAfter(dataFine)) ||
                (prenotazione.getDataInizio().isEqual(dataInizio) && prenotazione.getDataFine().isEqual(dataFine)) ||
                (prenotazione.getDataInizio().isEqual(dataInizio) && prenotazione.getDataFine().isBefore(dataFine))||
                (prenotazione.getDataInizio().isEqual(dataInizio) && prenotazione.getDataFine().isAfter(dataFine));
    }
}
