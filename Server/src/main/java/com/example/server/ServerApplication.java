package com.example.server;

import com.example.server.model.Cliente;
import com.example.server.model.Ombrellone;
import com.example.server.model.Prenotazione;
import com.example.server.service.ClienteService;
import com.example.server.service.OmbrelloneService;
import com.example.server.service.PrenotazioneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }


    @Bean
    CommandLineRunner run(ClienteService clienteService, PrenotazioneService prenotazioneService
    , OmbrelloneService ombrelloneService
                          ){
        return arg->{
            clienteService.saveUser(new Cliente("lollo@gmail.com","1234","Lorenzo","Luzi"));

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dataInizio =LocalDate.parse("2022-06-10",dtf);
            LocalDate dataFine =LocalDate.parse("2022-07-10",dtf);
            Prenotazione prenotazione=new Prenotazione(4,dataInizio,dataFine,"IN_ATTESA_DI_PAGAMENTO");
            prenotazioneService.save(prenotazione);
           clienteService.addPrenotazioneToClient(prenotazione,"lollo@gmail.com");
            Ombrellone ombrellone=new Ombrellone(1234);
            ombrelloneService.saveOmbrellone(ombrellone);

        };
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
