package com.example.server.repository;

import com.example.server.model.DettagliOrd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface DettagliOrdiRepo extends JpaRepository<DettagliOrd,Long> {
    @Query("SELECT d FROM DettagliOrd d WHERE d.ordinazione.id=?1")
    List<DettagliOrd> getDettagliOrdByIdOrdinazione(Long idOrdinazione);
}

