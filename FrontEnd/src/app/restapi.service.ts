import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable, retry } from 'rxjs';
import { Prenotazione } from './prenotazione';
import { Requestlogin } from './requestlogin';
import { ResponseConfig } from './response-config';
import { User } from './user';
import { UserAuthService } from './user-auth.service';

@Injectable({
  providedIn: 'root'
})
export class RestapiService {

  constructor(private http: HttpClient, private userAuth: UserAuthService) { }

  requestHedaers = new HttpHeaders(
    { "No-Auth": "True" }
  );

  public login(body: Requestlogin): Observable<ResponseConfig> {
    console.log(body);
    return this.http.post<ResponseConfig>("http://localhost:8080/cliente/login", body);
  }





  public getUser() {
    
    return this.http.get("http://localhost:8080/cliente/getAllClienti");
  }

  public getPrenotazioni(): Observable<Prenotazione[]> {
    return this.http.get<Prenotazione[]>("http://localhost:8080/cliente/prenotazione/getAll")
  }

}
