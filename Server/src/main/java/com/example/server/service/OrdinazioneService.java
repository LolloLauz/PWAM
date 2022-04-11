package com.example.server.service;

import com.example.server.model.Cliente;
import com.example.server.model.Ombrellone;
import com.example.server.model.Ordinazione;
import com.example.server.repository.ClienteRepo;
import com.example.server.repository.OmbrelloneRepo;
import com.example.server.repository.OrdinazioneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdinazioneService {

    @Autowired
    private OrdinazioneRepo ordinazioneRepo;

    @Autowired
    private ClienteRepo clienteRepo;

    @Autowired
    private OmbrelloneRepo ombrelloneRepo;


    public Ordinazione addNewOrdinazione(Ordinazione ordinazione){
        return ordinazioneRepo.save(ordinazione);
    }

    public Ordinazione addOrdinazioneCliente(Ordinazione ordinazione,String email){
        Cliente cliente=clienteRepo.findClienteByEmail(email);
        Ombrellone ombrellone=ombrelloneRepo.findOmbrelloneByQrCode(ordinazione.getQrCode());

        return
                cliente.addOrdinazione(ordinazione);
    }
}
