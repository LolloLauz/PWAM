package com.example.server.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.server.model.Cliente;
import com.example.server.model.Prenotazione;
import com.example.server.parteClient.AddOmbrelloneRequest;
import com.example.server.parteClient.AddUserRequest;
import com.example.server.parteClient.Request;
import com.example.server.parteClient.Response;
import com.example.server.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/cliente")

public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public Response logIn(@RequestBody Request request){
        UserDetails userDetails=clienteService.loadUserByUsername(request.getUsername());
//        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword());
//        authenticationManager.authenticate(authenticationToken);
        Algorithm algorithm=Algorithm.HMAC256("secret".getBytes());
//        UserDetails user=clienteService.loadUserByUsername(request.getUsername());

        String access_token= JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+10*60*1000))
                .sign(algorithm);
        return new Response(request.getUsername(),access_token);
    }

    @GetMapping("/getAllClienti")
    public List<Cliente> getAllClienti(){
        return clienteService.getAllClienti();
    }

    @GetMapping("/getPrenotazioniCliente")
    public List<Prenotazione> getPrenotazioneCliente(HttpServletRequest request){
        String email=getEmailFromToken(request.getHeader(AUTHORIZATION));
        return clienteService.getPrenotazioniCliente(email);
    }

    private String getEmailFromToken(String token) {
        token = token.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }

    @PostMapping("/register")
    public boolean registrazioneCliente(@RequestBody Cliente cliente){
        return clienteService.saveUser(cliente);
    }
}
