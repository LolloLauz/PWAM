import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ClienteServiceService } from '../cliente-service.service';
import { Ombrellone } from '../ombrellone';
import { Prenotazione } from '../prenotazione';

@Component({
  selector: 'app-prenotazioni-effettuate',
  templateUrl: './prenotazioni-effettuate.component.html',
  styleUrls: ['./prenotazioni-effettuate.component.css']
})
export class PrenotazioniEffettuateComponent implements OnInit {

  idCliente:number;
  prenotazioni :Prenotazione[];
  ombrelloni:Ombrellone[];
  flag:boolean=false;
  constructor(private router:ActivatedRoute,private clientService:ClienteServiceService) { }

  ngOnInit(): void {
    this.clientService.getPrenotazioniCliente().subscribe(data=>{
      this.prenotazioni=data;
    },
    error=>console.log(error));
  }

  listaOmbrelloni(idPrenotazione:number){
    this.clientService.getOmbrelloniPrenotazione(idPrenotazione).subscribe(data=>{
      console.log(data);
      this.ombrelloni=data;
    });
    if(this.ombrelloni!=null){
      this.flag=true;
    }else{
      this.flag=false;
    }
   }

   checkFlag(){
     return this.flag;
   }
}
