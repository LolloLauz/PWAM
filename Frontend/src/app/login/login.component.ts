import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ClienteServiceService } from '../cliente-service.service';
import { Requestlogin } from '../requestlogin';
import {RequestRegister} from "../request-register";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  // Login
  username: string;
  password: string;
  richistaAutenticazione: Requestlogin = new Requestlogin;
  toke: string;

  // Registrazione
 

  constructor(private clienteService:ClienteServiceService,private route:Router) { }

  ngOnInit(): void {
  }

  doLogin() {
    this.richistaAutenticazione.username = this.username;
    this.richistaAutenticazione.password = this.password;
    this.clienteService.login(this.richistaAutenticazione).subscribe(
      (data) => {
        localStorage.setItem("user",data.email);
        localStorage.setItem("token",data.token);
        const temp=localStorage.getItem("token")!;
        const tokeninfo = atob(temp.split('.')[1]);
          this.route.navigate(['/home']);
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
