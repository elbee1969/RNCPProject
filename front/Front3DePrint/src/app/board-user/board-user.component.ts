import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { TokenStorageService } from '../services/token-storage.service';
import { OrderService } from '../services/order-service';
import { Order } from '../model/Order';
import { FormBuilder } from '@angular/forms';
import { Status } from '../model/status';
import { User } from '../model/user';
import { AnonymousSubject } from 'rxjs/internal/Subject';
import { UploadFileService } from '../services/upload-file.service';
import { Router } from '@angular/router';
import { Image } from '../model/Image';
import { BillServiceService } from '../services/bill-service.service';
import { Bill } from '../model/Bill';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-board-user',
  templateUrl: './board-user.component.html',
  styleUrls: ['./board-user.component.css']
})
export class BoardUserComponent implements OnInit {
  content = '';
  username: any;
  orders: Order;
  validateForm: any;
  suppressForm: any;
  id: number;
  orderId: any;
  status: Status;
  C: Status;
  user: User;
  currentUser: any;
  imageId: any;
  image: any;
  data: Order;
  dataB: Bill;
  order: any;
  orderStatus: string;
  

  constructor(private userService: UserService,
              private tokenStorageService: TokenStorageService,
              private orderService: OrderService,
              private billService: BillServiceService,
              private router: Router,
              private imageService: UploadFileService) {

   }

  ngOnInit() {
    this.userService.getUserBoard().subscribe(
      data => {
        this.content = data;
        console.log('data in dashboard : ' + JSON.stringify(data));
        this.currentUser = this.tokenStorageService.getUser();
        this.id = JSON.parse(this.currentUser.userId);
        console.log('current user id : ' + this.id);
        //affiche tous les orders de l'utilisateur au status C
        this.orderService.getOrders(this.id, "V").subscribe(data => {
          this.orders = data;
          console.log("orders : " + JSON.stringify(data));
        },
          error => console.log(error)
        );
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );


  }


  annulOrder(orderId, imageId){
      
    this.orderService.deleteOrder(orderId)
      .subscribe(
        response => {
          console.log(response);
          this.image  = JSON.stringify({ status: "I", quantity: 1 });
          console.log("reset : " +this.image);
          return this.imageService.updateImage(this.image, imageId)
          .subscribe(
            () => {
              console.log('Image updated successfully');
              // this.router.navigate(['/upload']);
              this.ngOnInit();
            },
            error => {
              console.log(error);
            });
            
            
            
          },
          error => {
            console.log(error);
          });
          
         console.log("annul order " + orderId + " image : " + imageId);
         this.router.navigate(['/user']);
  }


  validateOrder(orderId) {

    this.order = JSON.stringify({ status: "A" });
    this.orderService.updateOrder(this.order, orderId)
      .subscribe(
        () => {
          console.log('order ' + orderId + ' updated successfully');
          return;
        },
        error => {
          console.log(error);
        });
    /*
    //this.orderStatus = JSON.stringify({ status: "V" });
    this.billService.createBill(this.id,'V').subscribe(result => {
      this.dataB = result;
      console.log("result " + JSON.stringify(result));
      console.log('bill created');
      return this.orderService.getOrders(this.id, "A")
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
              this.order = JSON.stringify({ status: "A" });
              
            }
            console.log("cpt : " + nb);
            console.log('get order successfully');
            console.log('result : ' + JSON.stringify(result));
          },
          error => {
            console.log(error);
          });
        },
        error => console.log(error)
        );
        */
        this.reloadPage();

  }

  isEmptyObject(obj) {
    return (obj && (Object.keys(obj).length === 0));
  }
  reloadPage() {
    //this.router.navigate['/user'];
    // window.location.reload();
    this.ngOnInit();
  };
}