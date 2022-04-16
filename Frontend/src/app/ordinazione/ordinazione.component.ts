import { Component, OnInit } from '@angular/core';
import { SelectMultipleControlValueAccessor } from '@angular/forms';
import { Router } from '@angular/router';
import { ClienteServiceService } from '../cliente-service.service';
import { OmbrelloneService } from '../ombrellone.service';
import { Ordinazione } from '../ordinazione';
import { OrdinazioneRequest } from '../ordinazione-request';
import { Prodotto } from '../prodotto';
import { ProdottoService } from '../prodotto.service';

@Component({
  selector: 'app-ordinazione',
  templateUrl: './ordinazione.component.html',
  styleUrls: ['./ordinazione.component.css']
})
export class OrdinazioneComponent implements OnInit {

  idProdotto:number;
  quantita:number;
  prodotti:Prodotto[];
  idProdotti:number[]=[];
  ordinazioneRequest:OrdinazioneRequest=new OrdinazioneRequest();
  codiciqrCode:number[]=[];
  codice:number;

  ordinazione:Ordinazione=new Ordinazione();

  constructor(private router:Router,private clienteService:ClienteServiceService,private ombrelloneService:OmbrelloneService,private prodottoService:ProdottoService) { }

  ngOnInit(): void {
    this.prodottoService.getAllProdotti().subscribe(data=>{
      this.prodotti=data;
      
    })
    this.ombrelloneService.getOmbrelloni().subscribe(data=>{
      
      data.forEach(element => {
        this.codiciqrCode.push(element.qrCode);
      });
    })
  }


  ordinaProdotto(idProdotto:number){
    this.idProdotti.push(idProdotto);
  }

  onSubmit(){
    this.saveOrdinazione();
    this.back();
  }

  getQuantita(idProdotto:number):number{
    let totale=0;
    this.idProdotti.forEach(element => {
      if(idProdotto==element){
        totale++;
      }
    });
    return totale;
  }

  saveOrdinazione(){
    this.ordinazione.stato="IN_ATTESA_DI_PAGAMENTO";
    this.ordinazione.statoConsegna="NON_CONSEGNATA";
    this.ordinazione.qrCode=this.codice;
    this.ordinazioneRequest.ordinazione=this.ordinazione;
    this.ordinazioneRequest.idProdotti=this.idProdotti;
    this.clienteService.addOrdinazione(this.ordinazioneRequest).subscribe(data=>{
      console.log(data);
    });
  }

  goToListOrder(){

    this.router.navigate(['/ordEffettuate']);
  }

  back(){
    setTimeout(()=>this.goToListOrder(),10);
  }

}
