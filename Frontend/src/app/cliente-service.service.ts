import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { OrdinazioneRequest } from './ordinazione-request';
import { Ordinazione } from './ordinazione';
import { Observable } from 'rxjs';
import { DettagliResponse } from './dettagli-response';
import { Prenotazione } from './prenotazione';
import { Ombrellone } from './ombrellone';
import { AddOmbrelloneRequest } from './add-ombrellone-request';
import { Requestlogin } from './requestlogin';
import { ResponseConfig } from './response-config';
import { OmbrelloniLiberiRequest } from './ombrelloni-liberi-request';
import {RequestRegister} from "./request-register";

@Injectable({
  providedIn: 'root'
})
export class ClienteServiceService {


  constructor(private httpClient:HttpClient) { }

  public login(body: Requestlogin): Observable<ResponseConfig> {
    console.log(body);
    return this.httpClient.post<ResponseConfig>("http://localhost:8080/cliente/login", body);
  }

  public register(body: RequestRegister): Observable<boolean> {
    console.log(body);
    return this.httpClient.post<boolean>("http://localhost:8080/cliente/register", body);
  }


  addOrdinazione(orderRequest:OrdinazioneRequest):Observable<Ordinazione>{
    return this.httpClient.post<Ordinazione>("http://localhost:8080/cliente/ordinazione/addProdottiToOrdinazione",orderRequest);
  }

  getOrdinazioni():Observable<Ordinazione[]>{
    return this.httpClient.get<Ordinazione[]>("http://localhost:8080/cliente/ordinazione/getOrdinazioniCliente");
  }

  getDettaglioOrdinazione(idOrdinazione:number):Observable<DettagliResponse[]>{
    return this.httpClient.get<DettagliResponse[]>(`http://localhost:8080/cliente/ordinazione/getDettaglioOrdinazione/`+`${idOrdinazione}`);
  }


  getOmbrelloniLiberi(dataInizio:Date,dataFine:Date):Observable<Ombrellone[]>{
    console.log(dataInizio.toLocaleString);
    return this.httpClient.get<Ombrellone[]>(`http://localhost:8080/cliente/prenotazione/getOmbrelloniLiberi/`+`${dataInizio}`+`/`+`${dataFine}`);
  }

  addOmbrelloneToPrenotazione(requestId:AddOmbrelloneRequest): Observable<Object>{
    console.log(requestId);
    return this.httpClient.post("http://localhost:8080/cliente/prenotazione/addOmbrellone",requestId);
  }

  getOmbrelloniPrenotazione(idPrenotazione:number):Observable<Ombrellone[]>{
      return this.httpClient.get<Ombrellone[]>(`http://localhost:8080/cliente/prenotazione/getOmbrelloniPrenotazione/`+`${idPrenotazione}`);
  }

  addPrenotazione(prenotazione:Prenotazione):Observable<number>{
      return this.httpClient.post<number>("http://localhost:8080/cliente/prenotazione/addPrenotazione",prenotazione);
  }


  getPrenotazioniCliente():Observable<Prenotazione[]>{
    return this.httpClient.get<Prenotazione[]>("http://localhost:8080/cliente/getPrenotazioniCliente");
  }
}
