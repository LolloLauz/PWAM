import { Component, OnInit } from '@angular/core';
import { RestapiService } from '../restapi.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  constructor(private restService:RestapiService) { }

  ngOnInit(): void {
  }


  public getAllUsers(){
    this.restService.getUser().subscribe(data=>{
      console.log(data);
    })
  }

  public getAllUserPrenotazioni(){
    this.restService.getPrenotazioni().subscribe(data=>{
      console.log(data);
    })
  }
}
