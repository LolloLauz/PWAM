import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ClienteServiceService } from '../cliente-service.service';
import { DettagliResponse } from '../dettagli-response';
import { Ordinazione } from '../ordinazione';

@Component({
  selector: 'app-ordinazioni-effettuate',
  templateUrl: './ordinazioni-effettuate.component.html',
  styleUrls: ['./ordinazioni-effettuate.component.css']
})
export class OrdinazioniEffettuateComponent implements OnInit {


  ordinazioni:Ordinazione[]=[];
  ordinazioniDetails:DettagliResponse[]=[];
  flag:boolean=false;

  totaleOrdinazione:number;

  constructor(private route:Router,private ordinazioneService:ClienteServiceService) { }

  ngOnInit() {
    this.getOrdinazioni();
    this.totaleOrdinazione = 0;
  }

  getOrdinazioni(){
    this.ordinazioneService.getOrdinazioni().subscribe(data=>{
          this.ordinazioni=data;
    });
  }

  ordinazioneDetail(idOrdinazione:number){
    this.ordinazioneService.getDettaglioOrdinazione(idOrdinazione).subscribe(data=>{
      this.ordinazioniDetails=data;
      console.log(data);
    });
    if(this.ordinazioniDetails!=null){
        this.flag=true;
    }
  }

  addTotale(prezzo:number){
    this.totaleOrdinazione += prezzo;
  }

  returnTotale(){
    return this.totaleOrdinazione;
  }

  setTotale(tot:number){
    this.totaleOrdinazione = tot;
  }

  checkflag(){
    return this.flag;
  }
}
