package com.example.server.repository;

import com.example.server.model.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdottoRepository extends JpaRepository<Prodotto,Long> {
}
