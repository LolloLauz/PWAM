import { temporaryAllocator } from '@angular/compiler/src/render3/view/util';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Requestlogin } from '../requestlogin';
import { RestapiService } from '../restapi.service';
import { UserAuthService } from '../user-auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string;
  password: string;
  richistaAutenticazione: Requestlogin = new Requestlogin;
  toke: string;

  constructor(private service: RestapiService, private route: Router, private userAuthService: UserAuthService) { }

  ngOnInit(): void {
  }


  doLogin() {
    this.richistaAutenticazione.username = this.username;
    this.richistaAutenticazione.password = this.password;
    this.service.login(this.richistaAutenticazione).subscribe(
      (data) => {
        localStorage.setItem("user",data.email);
        localStorage.setItem("token",data.token);
        const temp=localStorage.getItem("token")!;
        const tokeninfo = atob(temp.split('.')[1]);
        if (tokeninfo.match("ROLE_ADMIN")) {
          this.route.navigate(['/admin']);
        }else{
          this.route.navigate(['/user'])
        }
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
