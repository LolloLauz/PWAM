package com.example.server.parteClient;

import com.example.server.model.Prodotto;

public class DettaglioOrdResponse {
    private Prodotto prodotto;
    private int quantita;



    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public DettaglioOrdResponse(Prodotto prodotto, int quantita) {
        this.prodotto = prodotto;
        this.quantita = quantita;
    }

    public DettaglioOrdResponse() {
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    @Override
    public String toString() {
        return "DettaglioOrdinazioneResponse{" +
                "prodotto=" + prodotto +
                ", quantita=" + quantita +
                '}';
    }
}
