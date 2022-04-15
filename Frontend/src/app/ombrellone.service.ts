import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ombrellone } from './ombrellone';

@Injectable({
  providedIn: 'root'
})
export class OmbrelloneService {

  constructor(private httpClient:HttpClient) { }

  getOmbrelloneByqrCode(qrCode:number):Observable<Ombrellone>{
    return this.httpClient.get<Ombrellone>("http://localhost:80080/cliente/ombrellone/getOmbrelloneByqrCode/"+{qrCode});
  }

  getOmbrelloni(): Observable<Ombrellone[]>{
    return this.httpClient.get<Ombrellone[]>("http://localhost:8080/cliente/ombrellone/getAllOmbrelloni");
  }
}
