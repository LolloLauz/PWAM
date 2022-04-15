import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Prodotto } from './prodotto';

@Injectable({
  providedIn: 'root'
})
export class ProdottoService {

  constructor(private httpClient:HttpClient) { }


  getAllProdotti():Observable<Prodotto[]>{
    return this.httpClient.get<Prodotto[]>("http://localhost:8080/cliente/prodotto/getAllProdotti");
  }
}
