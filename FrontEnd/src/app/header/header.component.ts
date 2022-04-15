import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserAuthService } from '../user-auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private userAuthService:UserAuthService,private router:Router) { }

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
