package com.example.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Ombrellone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long qrCode;

    @JsonIgnore
    @ManyToMany(mappedBy = "ombrelloni")
    private Set<Prenotazione> prenotazioni=new HashSet<>();
    public Ombrellone() {
    }

    public Ombrellone(long qrCode) {
        this.qrCode = qrCode;
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

    public Set<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ombrellone that = (Ombrellone) o;
        return id == that.id && qrCode == that.qrCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, qrCode);
    }

    @Override
    public String toString() {
        return "Ombrellone{" +
                "id=" + id +
                ", qrCode=" + qrCode +
                '}';
    }
}
