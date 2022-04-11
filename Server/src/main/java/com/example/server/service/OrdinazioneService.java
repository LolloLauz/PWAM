package com.example.server.service;

import com.example.server.model.*;
import com.example.server.parteClient.DettaglioOrdResponse;
import com.example.server.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdinazioneService {

    @Autowired
    private OrdinazioneRepo ordinazioneRepo;

    @Autowired
    private ClienteRepo clienteRepo;

    @Autowired
    private OmbrelloneRepo ombrelloneRepo;

    @Autowired
    private ProdottoRepository prodottoRepo;

    @Autowired
    private DettagliOrdiRepo dettagliOrdiRepo;


//    public Ordinazione addNewOrdinazione(Ordinazione ordinazione){
//        return ordinazioneRepo.save(ordinazione);
//    }

    public Ordinazione addOrdinazioneCliente(Ordinazione ordinazione,String email){
        Cliente cliente=clienteRepo.findClienteByEmail(email);
        Ombrellone ombrellone=ombrelloneRepo.findOmbrelloneByQrCode(ordinazione.getQrCode());
        cliente.addOrdinazione(ordinazione);
        clienteRepo.save(cliente);
        return ordinazione;
    }

    public Ordinazione addProdottiToOrdinazione(Ordinazione ordinazione,Long[] idProdotti){
        riordinaVettore(idProdotti);
        System.out.println(ordinazione);
        int numero=1;
        for(int i=idProdotti.length-1;i>0;i--){
            if(idProdotti[i]!=(idProdotti[i-1])){
                Prodotto prodotto = prodottoRepo.findById(idProdotti[i]).orElseThrow();
                dettagliOrdiRepo.save(creaDettagliOrdinazione(ordinazione, prodotto, numero));
                numero=0;
            }
            numero++;
        }
        Prodotto prodotto=prodottoRepo.findById(idProdotti[0]).orElseThrow();
        dettagliOrdiRepo.save(creaDettagliOrdinazione(ordinazione,prodotto,numero));
        return ordinazioneRepo.save(ordinazione);
    }

    private DettagliOrd creaDettagliOrdinazione(Ordinazione ordinazione, Prodotto prodotto, int quantita) {
        DettagliOrd dettagliOrd=new DettagliOrd();
        dettagliOrdiRepo.save(dettagliOrd);
        dettagliOrd.setOrdinazione(ordinazione);
        dettagliOrd.setProdotto(prodotto);
        dettagliOrd.setQuantita(quantita);
        System.out.println(dettagliOrd);
        return dettagliOrd;
    }

    private void riordinaVettore(Long[] idProdotti) {
        for (int i = 0; i < idProdotti.length - 1; i++) {
            for (int j = i + 1; j < idProdotti.length; j++) {
                if (idProdotti[i] < idProdotti[j]) {
                    long z = idProdotti[i];
                    idProdotti[i] = idProdotti[j];
                    idProdotti[j] = z;
                }
            }
        }
    }

    public Ordinazione addNewOrdinazione(Ordinazione ordinazione,Long[] idProdotti,String email){
        Ordinazione ordinazioneTemp=creaOrdinazione(ordinazione,email);
        riordinaVettore(idProdotti);
        System.out.println(idProdotti.length);
        int numero=1;
        for(int i=idProdotti.length-1;i>0;i--){
            if(idProdotti[i]!=(idProdotti[i-1])){
                Prodotto prodotto = prodottoRepo.findById(idProdotti[i]).orElseThrow();
                dettagliOrdiRepo.save(creaDettagliOrdinazione(ordinazioneTemp, prodotto, numero));
                numero=0;
            }
            numero++;
        }
        Prodotto prodotto = prodottoRepo.findById(idProdotti[0]).orElseThrow();
        dettagliOrdiRepo.save(creaDettagliOrdinazione(ordinazioneTemp, prodotto, numero));
        return ordinazioneRepo.save(ordinazioneTemp);
    }
    private Ordinazione creaOrdinazione(Ordinazione ordinazione,String email){
        Cliente cliente=clienteRepo.findClienteByEmail(email);
        Ombrellone ombrellone=ombrelloneRepo.findOmbrelloneByQrCode(ordinazione.getQrCode());
        Ordinazione ordinazioneTemp=new Ordinazione();
        ordinazioneTemp.setQrCode(ombrellone.getQrCode());
        ordinazioneTemp.setStato(ordinazione.getStato());
        ordinazioneTemp.setStatoConsegna(ordinazione.getStatoConsegna());
        cliente.addOrdinazione(ordinazioneTemp);
        return ordinazioneTemp;
    }

    public List<Ordinazione> getAllOrdinazioni() {
        return ordinazioneRepo.findAll();
    }

    public List<Ordinazione> getOrdinazioniCliente(String email) {
        return clienteRepo.getOrdinazioniCliente(email);
    }

    public List<DettaglioOrdResponse> getDetails(Long idOrdinazione, String email) {
        if(clienteRepo.findClienteByEmail(email).getPrenotazioni().contains(ordinazioneRepo.getById(idOrdinazione))) {
            List<DettaglioOrdResponse> dettaglioOrdinazioneResponse = new ArrayList<>();
            System.out.println(dettagliOrdiRepo.getDettagliOrdByIdOrdinazione(idOrdinazione).size());
            for (DettagliOrd dettagliOrd : dettagliOrdiRepo.getDettagliOrdByIdOrdinazione(idOrdinazione)) {
                dettaglioOrdinazioneResponse.add(new DettaglioOrdResponse(dettagliOrd.getProdotto(), dettagliOrd.getQuantita()));
            }
            return dettaglioOrdinazioneResponse;
        }else{
            return null;
        }
    }
}
