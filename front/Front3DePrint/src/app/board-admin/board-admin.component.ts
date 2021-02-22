import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Bill } from '../model/Bill';
import { Order } from '../model/Order';
import { BillServiceService } from '../services/bill-service.service';
import { OrderService } from '../services/order-service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-board-admin',
  templateUrl: './board-admin.component.html',
  styleUrls: ['./board-admin.component.css']
})
export class BoardAdminComponent implements OnInit {
  content: string;
  bills: any;
  orders: Order;
  dataB: Bill;
  data: Order;
  id: number;
  order: any;
  orderStatus: any;

  constructor(private userService: UserService,
        private orderService: OrderService,
              private router: Router,
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
      this.billList();
   

    this.orderService.listOrders("V").subscribe(data => {
      this.orders = data;
      console.log("ordersA : " + JSON.stringify(data));
    },
      error => console.log(error)
    );
    
    
  }

 billList(){
      this.billService.listBills("I").subscribe(data => {
            this.bills = data;
          console.log('bills : ' + JSON.stringify(data))
          },
        error => console.log(error)
      );

    }


  createBill(id) {
    //this.orderStatus = JSON.stringify({ status: "V" });
    this.billService.createBill(id,"V").subscribe(result => {
      console.log('bill created');

       return this.orderService.getOrders(id, "V")
        .subscribe(
          (result) => {
            //this.data = JSON.stringify(result);
            this.data = result;
            // get the number of orders
            let nb = 0;
            for (let i in result) {
              nb++;
              console.log("key nbr :" + i);
              console.log("id value : " + result[i].id);
              console.log("status value : " + result[i].status);
            }
            console.log(" keys by record : " + nb);
            // modify order status from V to A

            for (let i = 0; i < nb; i++) {
              this.orderStatus = JSON.stringify({ status: "O" });
                  this.orderService.updateOrder(this.orderStatus, result[i].id)
                  .subscribe(
                    () => {
                      console.log('order ' + result[i].id + ' updated successfully');
                     },
                    error => {
                      console.log(error);
                    });
            }
            console.log("cpt : " + nb);
            console.log('get order successfully');
            console.log('result : ' + JSON.stringify(result));
            this.reloadPage();
          },
          error => {
            console.log(error);
          });
        },
        error => console.log(error)
    );  

  }

  reloadPage() {
    this.ngOnInit();
    this.router.navigate['/admin'];
    window.location.reload();
  }
    

  isEmptyObject(obj) {
    return (obj && (Object.keys(obj).length === 0));
  }

  
}