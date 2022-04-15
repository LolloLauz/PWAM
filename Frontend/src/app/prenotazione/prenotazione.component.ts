import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AddOmbrelloneRequest } from '../add-ombrellone-request';
import { ClienteServiceService } from '../cliente-service.service';
import { Ombrellone } from '../ombrellone';
import { OmbrelloniLiberiRequest } from '../ombrelloni-liberi-request';
import { Prenotazione } from '../prenotazione';

@Component({
  selector: 'app-prenotazione',
  templateUrl: './prenotazione.component.html',
  styleUrls: ['./prenotazione.component.css']
})
export class PrenotazioneComponent implements OnInit {

  constructor(private clienteService:ClienteServiceService,private route:Router) { }

  dataInizio:Date;
  dataFine:Date;
  numSdraio:number;
  prenotazione:Prenotazione =new Prenotazione();
  ombrelloni:Ombrellone[];
  idOmbrelloni:number[]=[];
  idPrenotazione:number;
  addOmbrelloneRequest:AddOmbrelloneRequest=new AddOmbrelloneRequest();
  ombrelloniSelezionati:number;
  ombrelloniLiberiRequest:OmbrelloniLiberiRequest=new OmbrelloniLiberiRequest();
  flag:boolean=false;

  ngOnInit(): void {
  }

  onSubmit(){
    this.prenotazione.stato="IN_ATTESA_DI_PAGAMENTO";
    this.clienteService.addPrenotazione(this.prenotazione).subscribe(data=>{
        this.addOmbrelloneRequest.idPrenotazione=data;
        this.idOmbrelloni.forEach(element => {
          this.addOmbrelloneRequest.idOmbrellone=element;
          this.addOmbrelloniToPrenotazione(this.addOmbrelloneRequest);
        });

    });
    setTimeout(()=>this.goToPrenotazioniEffettuate(),10);


  }

  goToPrenotazioniEffettuate(){
    this.route.navigate(['/preEffettuate']);
  }
  mostraOmbrelloniLiberi(){
    this.clienteService.getOmbrelloniLiberi(this.prenotazione.dataInizio,this.prenotazione.dataFine).subscribe(data=>{
      this.ombrelloni=data;
    })
    this.flag=true;
  }

  mostraTabella(){
    return this.flag;
  }

  prenotaOmbrellone(idOmbrellone:number){
    this.idOmbrelloni.push(idOmbrellone);
    this.ombrelloniSelezionati=this.idOmbrelloni.length;
    this.ombrelloni.forEach((element,index) => {
      if(element.id==idOmbrellone) this.ombrelloni.splice(index,1);
    });
  }

  togliOmbrelloni(){
    this.mostraOmbrelloniLiberi();
    this.idOmbrelloni.length=0;
    this.ombrelloniSelezionati=this.idOmbrelloni.length;
  }

  addOmbrelloniToPrenotazione(addOmbrelloneRequest:AddOmbrelloneRequest){
      this.clienteService.addOmbrelloneToPrenotazione(this.addOmbrelloneRequest).subscribe();
  }
}
