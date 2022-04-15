package com.example.server.repository;

import com.example.server.model.Ordinazione;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdinazioneRepo extends JpaRepository<Ordinazione, Long> {
}
