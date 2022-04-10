package com.example.server.model;

import javax.persistence.*;

@Entity
public class DettagliOrd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantita;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_ordinazione",referencedColumnName = "id")
    private Ordinazione ordinazione;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_prodotto",referencedColumnName = "id")
    private Prodotto prodotto;

    public DettagliOrd(int quantita) {
        this.quantita = quantita;
    }

    public DettagliOrd() {
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public Ordinazione getOrdinazione() {
        return ordinazione;
    }

    public void setOrdinazione(Ordinazione ordinazione) {
        this.ordinazione = ordinazione;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DettagliOrdinazione{" +
                "id=" + id +
                ", quantita=" + quantita +
                ", ordinazione=" + ordinazione +
                ", prodotto=" + prodotto +
                '}';
    }
}

