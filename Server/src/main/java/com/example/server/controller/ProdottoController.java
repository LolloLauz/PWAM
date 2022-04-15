package com.example.server.controller;

import com.example.server.model.Prodotto;
import com.example.server.service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente/prodotto")
public class ProdottoController {

    @Autowired
    private ProdottoService prodottoService;

    @PostMapping("addNewProdotto")
    public void addNewProdotto(@RequestBody Prodotto prodotto){
        prodottoService.addNewProdotto(prodotto);
    }

    @GetMapping("/getAllProdotti")
    public List<Prodotto> getAllProdotti(){
        return prodottoService.getAllProdotti();
    }
}

