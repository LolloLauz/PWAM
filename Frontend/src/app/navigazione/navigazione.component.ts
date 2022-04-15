import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-navigazione',
  templateUrl: './navigazione.component.html',
  styleUrls: ['./navigazione.component.css']
})
export class NavigazioneComponent implements OnInit {

  constructor(private userAuthService:AuthService,private router:Router) { }

  ngOnInit(): void {
  }

  public isLoggin(){
    return this.userAuthService.isLoggedIn();
  }

  public logout(){
    this.userAuthService.clear();
    this.router.navigate(["/home"]);
  }

}
