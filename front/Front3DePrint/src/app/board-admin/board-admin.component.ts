import { Component, OnInit } from '@angular/core';
import { BillServiceService } from '../services/bill-service.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-board-admin',
  templateUrl: './board-admin.component.html',
  styleUrls: ['./board-admin.component.css']
})
export class BoardAdminComponent implements OnInit {
  content: string;

  constructor(private userService: UserService,
              private billService: BillServiceService
            ) { }

  ngOnInit() {
    this.userService.getAdminBoard().subscribe(
      data => {
        this.content = JSON.stringify(data);
        console.log("thiscontent" + this.content);
      },
      err => {
        this.content = err.error.message;
      }
    )
  }





  
}