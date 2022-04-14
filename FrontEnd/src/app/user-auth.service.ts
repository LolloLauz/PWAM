import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class UserAuthService {

  constructor() { }

  public clear() {
    localStorage.clear();
  }

  public isLoggedIn() {
    return localStorage.getItem("user") && localStorage.getItem("token");
  }

}
