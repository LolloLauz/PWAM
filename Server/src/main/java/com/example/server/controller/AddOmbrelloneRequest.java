package com.example.server.controller;

public class AddOmbrelloneRequest {

    private Long idOmbrellone;
    private Long idPrenotazione;

    public Long getIdOmbrellone() {
        return idOmbrellone;
    }

    public void setIdOmbrellone(Long idOmbrellone) {
        this.idOmbrellone = idOmbrellone;
    }

    public Long getIdPrenotazione() {
        return idPrenotazione;
    }

    public void setIdPrenotazione(Long idPrenotazione) {
        this.idPrenotazione = idPrenotazione;
    }

    public AddOmbrelloneRequest(Long idOmbrellone, Long idPrenotazione) {
        this.idOmbrellone = idOmbrellone;
        this.idPrenotazione = idPrenotazione;
    }

    public AddOmbrelloneRequest() {
    }
}
