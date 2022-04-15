package com.example.server.repository;

import com.example.server.model.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrenotazioneRepo extends JpaRepository<Prenotazione,Long> {
}
