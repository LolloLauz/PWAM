package com.example.server.repository;

import com.example.server.model.Ombrellone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OmbrelloneRepo extends JpaRepository<Ombrellone,Long> {

    Ombrellone findOmbrelloneByQrCode(Long qrCode);
}
