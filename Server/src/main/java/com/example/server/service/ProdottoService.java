package com.example.server.service;

import com.example.server.model.Prodotto;
import com.example.server.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdottoService {

    @Autowired
    private ProdottoRepository prodottoRepository;


    public Prodotto addNewProdotto(Prodotto prodotto){
        return prodottoRepository.save(prodotto);
    }

    public List<Prodotto> getAllProdotti(){
        return prodottoRepository.findAll();
    }
}
