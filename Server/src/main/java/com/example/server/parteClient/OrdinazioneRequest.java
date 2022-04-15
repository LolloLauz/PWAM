package com.example.server.parteClient;

import com.example.server.model.Ordinazione;

public class OrdinazioneRequest {

    private Ordinazione ordinazione;
    private Long[] idProdotti;

    public OrdinazioneRequest(Ordinazione ordinazione, Long[] idProdotti) {
        this.ordinazione = ordinazione;
        this.idProdotti = idProdotti;

    }

    public OrdinazioneRequest() {
    }



    public Ordinazione getOrdinazione() {
        return ordinazione;
    }

    public void setOrdinazione(Ordinazione ordinazione) {
        this.ordinazione = ordinazione;
    }

    public Long[] getIdProdotti() {
        return idProdotti;
    }

    public void setIdProdotti(Long[] idProdotti) {
        this.idProdotti = idProdotti;
    }
}
