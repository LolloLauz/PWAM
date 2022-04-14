package com.example.server.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.server.model.Ombrellone;
import com.example.server.model.Prenotazione;
import com.example.server.parteClient.AddOmbrelloneRequest;
import com.example.server.parteClient.OmbrelloniLiberiRequest;
import com.example.server.service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


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

    @PostMapping("/addPrenotazione")
    public Long addPrneotazione(@RequestBody Prenotazione prenotazione, HttpServletRequest request){
        String email=getEmailFromToken(request.getHeader(AUTHORIZATION));
        return prenotazioneService.addNewPrenotazione(prenotazione,email).getId();
    }

    @GetMapping("/getOmbrelloniLiberi/{dataIni}/{dataFin}")
    public List<Ombrellone> getOmbrelloniLiberi(@PathVariable String dataIni,@PathVariable String dataFin){
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataInizio=LocalDate.parse(dataIni,dtf);
        LocalDate dataFine=LocalDate.parse(dataFin,dtf);
        return prenotazioneService.getOmbrelloniLiberiInUnPeriodo(dataInizio,dataFine);
    }

    //TODO:controllo se l'id della prenotazione corrisponde ad una prenotazione associata al token di quel cliente
    @GetMapping("/getOmbrelloniPrenotazione/{idPrenotazione}")
    public Set<Ombrellone> getOmbrelloniPrenotazione(@PathVariable Long idPrenotazione,HttpServletRequest request){
        String email=getEmailFromToken(request.getHeader(AUTHORIZATION));
        return prenotazioneService.getOmbrelloniPrenotazione(idPrenotazione,email);
    }

    private String getEmailFromToken(String token) {
        token = token.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }
}
