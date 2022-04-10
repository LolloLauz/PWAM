package com.example.server.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.server.model.Cliente;
import com.example.server.model.Prenotazione;
import com.example.server.repository.ClienteRepo;
import com.example.server.repository.PrenotazioneRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Slf4j
@Service
public class ClienteService implements UserDetailsService {

    @Autowired
    private ClienteRepo clienteRepo;

    @Autowired
    private PrenotazioneRepo prenotazioneRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ClienteService() {
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente=clienteRepo.findClienteByEmail(email);
        if(cliente==null){
            log.error("User not found");
            throw new UsernameNotFoundException("Cliente non trovato nel DB");
        }else{
            log.info("Cliente trovato");
        }

        return new User(cliente.getEmail(),cliente.getPassword(),new ArrayList<>());
    }

    public Cliente saveUser(Cliente cliente){
        log.info("saving user {} to the database",cliente.getNome());
        cliente.setPassword(passwordEncoder.encode(cliente.getPassword()));
        return clienteRepo.save(cliente);
    }

    public Cliente getCliente(String email){
        log.info("Fetching cliente {}",email);
        return clienteRepo.findClienteByEmail(email);
    }

    public Prenotazione addPrenotazioneToClient(Prenotazione prenotazione, String email){
        Cliente cliente=clienteRepo.findClienteByEmail(email);
        cliente.addPrenotazione(prenotazione);
        clienteRepo.save(cliente);
        return prenotazioneRepo.save(prenotazione);
    }


    public List<Cliente> getAllClienti() {
        return clienteRepo.findAll();
    }

    public List<Prenotazione> getPrenotazioniCliente(String email) {
        return clienteRepo.getPrenotazioniCliente(email);
    }
}
