package com.example.server.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int num_sdraio;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private String stato;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "dettagli_prenotazione",
            joinColumns = @JoinColumn(name = "id_prenotazione"),
            inverseJoinColumns = @JoinColumn(name="id_ombrellone"))
    private Set<Ombrellone> ombrelloni=new HashSet<>();

    public Prenotazione() {
    }

    public Prenotazione(int num_sdraio, LocalDate dataInizio, LocalDate dataFine, String stato) {
        this.num_sdraio = num_sdraio;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.stato = stato;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNum_sdraio() {
        return num_sdraio;
    }

    public void setNum_sdraio(int num_sdraio) {
        this.num_sdraio = num_sdraio;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public Set<Ombrellone> getOmbrelloni() {
        return ombrelloni;
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "id=" + id +
                ", num_sdraio=" + num_sdraio +
                ", dataInizio=" + dataInizio +
                ", dataFine=" + dataFine +
                ", stato='" + stato + '\'' +
                '}';
    }

    public void addOmbrellone(Ombrellone ombrellone) {
        ombrelloni.add(ombrellone);
    }
}
