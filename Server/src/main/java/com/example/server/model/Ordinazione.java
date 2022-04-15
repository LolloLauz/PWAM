package com.example.server.model;

import javax.persistence.*;


@Entity
public class Ordinazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long qrCode;
    private String stato;
    private String statoConsegna;

    public Ordinazione() {
    }

    public Ordinazione(long qrCode, String stato, String statoConsegna) {
        this.qrCode = qrCode;
        this.stato = stato;
        this.statoConsegna = statoConsegna;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQrCode() {
        return qrCode;
    }

    public void setQrCode(long qrCode) {
        this.qrCode = qrCode;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getStatoConsegna() {
        return statoConsegna;
    }

    public void setStatoConsegna(String statoConsegna) {
        this.statoConsegna = statoConsegna;
    }

    @Override
    public String toString() {
        return "Ordinazione{" +
                "id=" + id +
                ", qrCode=" + qrCode +
                ", stato='" + stato + '\'' +
                ", statoConsegna='" + statoConsegna + '\'' +
                '}';
    }
}
