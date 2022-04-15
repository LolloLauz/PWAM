package com.example.server;

import com.example.server.model.*;
import com.example.server.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }


    @Bean
    CommandLineRunner run(ClienteService clienteService, PrenotazioneService prenotazioneService
    , OmbrelloneService ombrelloneService, OrdinazioneService ordinazioneService
                          , ProdottoService prodottoService
                          ){
        return arg->{
            clienteService.saveUser(new Cliente("lollo@gmail.com","1234","Lorenzo","Luzi"));
            clienteService.saveUser(new Cliente("mario@gmail.com","1234","Lorenzo","Luzi"));

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dataInizio =LocalDate.parse("2022-06-10",dtf);
            LocalDate dataFine =LocalDate.parse("2022-07-10",dtf);
            Prenotazione prenotazione=new Prenotazione(4,dataInizio,dataFine,"IN_ATTESA_DI_PAGAMENTO");
            prenotazioneService.save(prenotazione);
            clienteService.addPrenotazioneToClient(prenotazione,"lollo@gmail.com");
            for(int i=0;i<20;i++){
                ombrelloneService.saveOmbrellone(new Ombrellone(i));
            }
            Prodotto acqua = new Prodotto("acqua", 20, 1.00);
            prodottoService.addNewProdotto(acqua);
            Prodotto birra = new Prodotto("birra", 20, 2.00);
            prodottoService.addNewProdotto(birra);


            Ordinazione ordinazione = new Ordinazione(1234, "IN_ATTESA_DI_PAGAMENTO", "IN_ATTESA_DI_CONSEGNA");
//            ordinazioneService.addNewOrdinazione(ordinazione);
//            Long[] prodotti={Long.valueOf(1), Long.valueOf(2)};
//            ordinazioneService.addProdottiToOrdinazione(ordinazione,prodotti);
//            ordinazioneService.addOrdinazioneCliente(ordinazione,"lollo@gmail.com");

        };
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("GET","POST","PUT","OPTION","DELETE")
                        .allowedHeaders("*")
                        .allowedOriginPatterns("*")
                        .allowCredentials(true);
            }
        };
    }
}
