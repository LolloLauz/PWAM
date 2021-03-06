import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }
  public clear() {
    localStorage.clear();
  }

  public isLoggedIn() {
    return localStorage.getItem("user") && localStorage.getItem("token");
  }
}
