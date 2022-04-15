package com.example.server.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.server.model.Ordinazione;
import com.example.server.parteClient.DettaglioOrdResponse;
import com.example.server.parteClient.OrdinazioneRequest;
import com.example.server.service.OrdinazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/cliente/ordinazione")
public class OrdinazioneController {


    @Autowired
    private OrdinazioneService ordinazioneService;

    @GetMapping("/getAllOrdinazioni")
    public List<Ordinazione> getAllOrdinazioni(){
        return ordinazioneService.getAllOrdinazioni();
    }

    @GetMapping("/getOrdinazioniCliente")
    public List<Ordinazione> gettOrdinazioniCliente(HttpServletRequest request){
        String email=getEmailFromToken(request.getHeader(AUTHORIZATION));
        return ordinazioneService.getOrdinazioniCliente(email);
    }


    @PostMapping("/addProdottiToOrdinazione")
    public Ordinazione addNewOrdinazione(@RequestBody OrdinazioneRequest ordinazioneRequest, HttpServletRequest request){
        String email=getEmailFromToken(request.getHeader(AUTHORIZATION));
        return  ordinazioneService.addNewOrdinazione(ordinazioneRequest.getOrdinazione(),ordinazioneRequest.getIdProdotti(),email);
    }

    private String getEmailFromToken(String token) {
        token = token.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }

    @GetMapping("/getDettaglioOrdinazione/{idOrdinazione}")
    public List<DettaglioOrdResponse> getDettaglioOrdinazione(@PathVariable Long idOrdinazione,HttpServletRequest request){
        String email=getEmailFromToken(request.getHeader(AUTHORIZATION));
        return ordinazioneService.getDetails(idOrdinazione,email);
    }
}
