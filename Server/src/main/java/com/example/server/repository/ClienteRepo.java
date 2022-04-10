package com.example.server.repository;

import com.example.server.model.Cliente;
import com.example.server.model.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepo extends JpaRepository<Cliente,Long> {

    Cliente findClienteByEmail(String email);

    Cliente findClienteById(Long id);
    @Query("SELECT c.prenotazioni FROM Cliente c WHERE c.email=?1")
    List<Prenotazione> getPrenotazioniCliente(String email);
}
