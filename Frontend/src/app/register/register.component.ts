import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ClienteServiceService } from '../cliente-service.service';
import { RequestRegister } from '../request-register';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  nome: string;
  cognome: string;
  email: string;
  psw: string;
  richistaRegistrazione: RequestRegister = new RequestRegister();

  constructor(private clienteService: ClienteServiceService, private router: Router) { }

  ngOnInit(): void {
  }

  doRegister() {
    if (this.nome != null && this.cognome != null && this.email != null && this.psw != null) {
      this.richistaRegistrazione.nome = this.nome;
      this.richistaRegistrazione.cognome = this.cognome;
      this.richistaRegistrazione.email = this.email;
      this.richistaRegistrazione.password = this.psw;
      this.clienteService.register(this.richistaRegistrazione).subscribe(
        (data) => {
          console.log(data);
          if(data){
          alert("Registrato!");
          this.router.navigate(["/login"]);
          }else{
            alert("l'email che stai provando ad utilizzare è gia stata utilizzata");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    }else{
      alert("Devi riempire tutti i campi per poterti registrare")
    }
  }

}
