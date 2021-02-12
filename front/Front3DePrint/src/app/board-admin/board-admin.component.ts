import { Component, OnInit } from '@angular/core';
import { Bill } from '../model/Bill';
import { BillServiceService } from '../services/bill-service.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-board-admin',
  templateUrl: './board-admin.component.html',
  styleUrls: ['./board-admin.component.css']
})
export class BoardAdminComponent implements OnInit {
  content: string;
  bills: any;

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
    this.billService.listBills().subscribe(
        bills => {
          this.bills = bills;
        console.log('bills : ' + JSON.stringify(bills))
        }
      )


  }



  isEmptyObject(obj) {
    return (obj && (Object.keys(obj).length === 0));
  }

  
}