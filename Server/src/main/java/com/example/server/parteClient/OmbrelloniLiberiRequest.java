package com.example.server.parteClient;

import java.time.LocalDate;

public class OmbrelloniLiberiRequest {
    private String dataInizio;
    private String dataFine;

    @Override
    public String toString() {
        return "OmbrelloniLiberiRequest{" +
                "dataInizio='" + dataInizio + '\'' +
                ", dataFine='" + dataFine + '\'' +
                '}';
    }

    public String getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(String dataInizio) {
        this.dataInizio = dataInizio;
    }

    public String getDataFine() {
        return dataFine;
    }

    public void setDataFine(String dataFine) {
        this.dataFine = dataFine;
    }

    public OmbrelloniLiberiRequest(String dataInizio, String dataFine) {
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }

    public OmbrelloniLiberiRequest() {
    }
}
